import express from 'express';
import cors from 'cors';
import { getPatients, getPatient, addPatient, deletePatient, getPatientsByName, getAilmentIdByName, getAllAilments, getAllVisits, addVisit, getDoctorsForAilment, getVisitDetails, getPrescribedDrugs, getDrugIdByName, prescribeDrug, checkVisitExists, getLowDrugs, addShipment, addShipmentDrug, addDrugToInventory, addTransaction, addTransactionDrug, getDrugPriceById, subtractDrugFromInventory, getQuantityDrugById, getDoctorRateFromId, getAllDrugs } from './database2.js';

const app = express();

// Middleware to parse JSON request body
app.use(express.json());

// Enable CORS if needed
app.use(cors());

// Get all patients or filter patients by first name and last name
app.get("/patients", async (req, res) => {
    const { first_name, last_name } = req.query;  // Retrieve query parameters

    if (first_name || last_name) {
        try {
            // Call the function to get filtered patients by first name and last name
            const patients = await getPatientsByName(first_name, last_name);
            res.json(patients);  // Return the filtered list of patients
        } catch (error) {
            console.error('Error fetching filtered patients:', error);
            res.status(500).send("Error fetching patients");
        }
    } else {
        // If no filter parameters, get all patients
        try {
            const patients = await getPatients();
            res.json(patients);
        } catch (error) {
            console.error('Error fetching all patients:', error);
            res.status(500).send("Error fetching all patients");
        }
    }
});

// Get a single patient by ID
app.get("/patients/:id", async (req, res) => {
    const id = req.params.id;
    try {
        const patient = await getPatient(id);
        if (!patient) {
            return res.status(404).send("Patient not found");
        }
        res.json(patient);
    } catch (error) {
        console.error('Error fetching patient by ID:', error);
        res.status(500).send("Error fetching patient by ID");
    }
});

// Add a new patient
app.post("/patients", async (req, res) => {
    const { last_name, first_name, gender, birthdate, mobile_number, email_address } = req.body;
    try {
        const result = await addPatient(last_name, first_name, gender, birthdate, mobile_number, email_address);
        res.status(201).json({
            patient_id: result.insertId,
            last_name, first_name, gender, birthdate, mobile_number, email_address
        });
    } catch (error) {
        console.error('Error adding patient:', error);
        res.status(500).send('Error adding patient');
    }
});

// Delete a patient by ID
app.delete("/patients/:id", async (req, res) => {
    const { id } = req.params;
    try {
        const result = await deletePatient(id);
        if (result.affectedRows === 0) {
            return res.status(404).send('Patient not found');
        }
        res.send('Patient deleted successfully');
    } catch (error) {
        console.error('Error deleting patient:', error);
        res.status(500).send('Error deleting patient');
    }
});

app.get("/ailments/:name", async (req, res) => {
    const { name } = req.params;

    try {
        const ailmentId = await getAilmentIdByName(name);
        if (ailmentId) {
            res.json({ ailment_id: ailmentId });
        } else {
            res.status(404).send('Ailment not found');
        }
    } catch (error) {
        console.error('Error fetching ailment:', error);
        res.status(500).send('Error fetching ailment');
    }
});

// Add a visit
app.post("/visits", async (req, res) => {
    const { patient_id, log_in, ailment, doctor_id } = req.body;

    try {
        console.log('Ailment:', ailment); // Log the ailment name received
        
        // Step 1: Get the ailment_id from the ailment reference table
        const ailmentResult = await getAilmentIdByName(ailment);

        if (!ailmentResult) {
            return res.status(404).send('Ailment not found');
        }

        console.log('Ailment ID:', ailmentResult.ailment_id); // Log the retrieved ailment ID

        const ailment_id = ailmentResult.ailment_id; // Assuming result has ailment_id

        // Step 2: Insert the visit into the visits table
        const visitResult = await addVisit(patient_id, log_in, ailment_id, doctor_id);
        console.log(visitResult.insertId);

        const consultRate = await getDoctorRateFromId(doctor_id);

        const transactionResult = addTransaction(visitResult.insertId, consultRate[0].consultation_rate)

        res.status(201).json({
            visit_id: visitResult.insertId,
            patient_id, 
            log_in, 
            ailment_id
        });
    } catch (error) {
        console.error('Error recording visit:', error); // Log the error
        res.status(500).send('Error recording visit');
    }
});

// Get all ailments
app.get("/ailments", async (req, res) => {
    try {
        const ailments = await getAllAilments();  // Fetching all ailments
        res.json(ailments);
    } catch (error) {
        console.error('Error fetching ailments:', error);
        res.status(500).send('Error fetching ailments');
    }
});

// Get all visits
app.get("/visits", async (req, res) => {
    try {
        const visits = await getAllVisits();  // Fetching all visits
        res.json(visits);
    } catch (error) {
        console.error('Error fetching visits:', error);
        res.status(500).send('Error fetching visits');
    }
});

app.get("/ailments/:name", async (req, res) => {
    const { name } = req.params;

    try {
        // Step 1: Get the ailment details (ailment_id and speci_id) based on illness name
        const ailmentResult = await getAilmentIdByName(name);
        if (!ailmentResult) {
            return res.status(404).send('Ailment not found');
        }

        // Return the ailment ID and the specialist ID (speci_id)
        res.json({
            ailment_id: ailmentResult.ailment_id,
            speci_id: ailmentResult.speci_id
        });
    } catch (error) {
        console.error('Error fetching ailment:', error);
        res.status(500).send('Error fetching ailment');
    }
});

// New Endpoint: Get doctors who specialize in a specific ailment
app.get("/doctors/for-ailment/:name", async (req, res) => {
    const { name } = req.params;

    try {
        // Step 1: Get the ailment details (speci_id) based on illness name
        const ailmentResult = await getAilmentIdByName(name);
        if (!ailmentResult) {
            return res.status(404).send('Ailment not found');
        }

        const speci_id = ailmentResult.speci_id; // Get the speci_id (specialization ID)

        // Step 2: Get doctors who specialize in this `speci_id`
        const doctors = await getDoctorsForAilment(speci_id);

        if (doctors.length === 0) {
            return res.status(404).send('No doctors found for this ailment');
        }

        // Step 3: Return the doctors' names (or full details as needed)
        res.json(doctors); // This will return the list of doctors specializing in the ailment
    } catch (error) {
        console.error('Error fetching doctors for ailment:', error);
        res.status(500).send('Error fetching doctors for the selected ailment');
    }
});

app.get("/diagnosis/:id", async (req, res) => {
    const patientId = parseInt(req.params.id, 10);

    if (isNaN(patientId)) {
        return res.status(400).send("Invalid patient ID");
    }

    try {
        // Fetch visit details using the getVisitDetails function
        const details = await getVisitDetails(patientId);

        if (details.length === 0) {
            return res.status(404).send("No diagnosis found for this patient");
        }

        // Return the visit details as JSON
        res.json(details);
    } catch (error) {
        console.error("Error fetching diagnosis:", error.message);
        res.status(500).send("Error fetching diagnosis");
    }
});

app.post("/prescriptions/add", async (req, res) => {
    const { visit_id, drug_name, amount } = req.body;

    console.log("POST /prescriptions/add called with:", req.body); // Debugging

    // Validate input
    if (!visit_id || isNaN(parseInt(visit_id, 10))) {
        return res.status(400).json({ error: "Invalid visit ID" });
    }

    try {
        // Step 1: Check if the visit ID exists
        const visitExists = await checkVisitExists(visit_id);
        if (!visitExists) {
            return res.status(404).json({ error: "Visit ID not found" });
        }

        // Step 2: Fetch drug_id using the drug_name
        const drug = await getDrugIdByName(drug_name);
        if (!drug) {
            return res.status(404).json({ error: "Drug not found" });
        }

        // Step 3: Insert into prescribed_drugs
        const result = await prescribeDrug(visit_id, drug.drug_id, amount);

        // Step 4: Send success response
        res.status(201).json({
            message: "Drug prescribed successfully",
            visit_id,
            drug_id: drug.drug_id,
            amount,
        });
    } catch (error) {
        console.error("Error prescribing drug:", error);
        res.status(500).json({ error: "Error prescribing drug" });
    }
});

app.get('/prescriptions/:visit_id', async (req, res) => {
    const visitId = parseInt(req.params.visit_id, 10);

    if (isNaN(visitId)) {
        return res.status(400).send('Invalid visit ID');
    }

    try {
        const drugs = await getPrescribedDrugs(visitId);
        res.json(drugs); // Return the drugs as JSON
    } catch (error) {
        console.error('Error in /prescriptions/:visit_id:', error.message);
        res.status(500).send('Failed to fetch prescribed drugs');
    }
});

app.get('/lowinventory', async (req, res) => {
    const lowDrugs = await getLowDrugs();
    res.json(lowDrugs);
});

app.get('/inventory', async (req, res) => {
    const drugs = await getAllDrugs();
    res.json(drugs);
})

app.post("/shipments", async (req, res) => {
    const { shipmentDate, shipmentCost, drugs } = req.body;

    if (!shipmentDate || !shipmentCost || !Array.isArray(drugs) || drugs.length === 0) {
        return res.status(400).json({ error: 'Missing required fields or drugs array is empty' });
    }

    try {
        // Step 1: Insert the shipment details into the `shipments` table
        const shipmentResult = await addShipment(shipmentDate, shipmentCost);

        // Step 2: For each drug in the shipment, insert the drug details into the `shipment_drugs` table
        const shipmentId = shipmentResult[0].insertId;  // Assuming the `addShipment` function returns the insertId
        for (const drug of drugs) {
            const { name, quantity, price } = drug;
            const drugId = await getDrugIdByName(name);
            console.log(drugId.drug_id);
            await addShipmentDrug(shipmentId, drugId.drug_id, quantity, price);
            const addDrugsResult = await addDrugToInventory(drugId.drug_id, quantity);
            console.log(drugId.drug_id);
            console.log(addDrugsResult);
        }

        res.status(201).json({ message: 'Shipment and drugs added successfully' });

    } catch (error) {
        console.error('Error inserting shipment and drugs:', error);
        res.status(500).json({ error: 'Failed to add shipment and drugs' });
    }
});

app.post("/transactions", async (req, res) => {
    const { visitId, drugs } = req.body;

    if (!visitId || !Array.isArray(drugs) || drugs.length === 0) {
        return res.status(400).json({ error: 'Missing required fields or drugs array is empty' });
    }

    var totalAmount = 0;

    for (const drug of drugs) {
        const { name, quantity } = drug;
        const drugId = await getDrugIdByName(name);
        var drugQty;

        try {
            drugQty = await getQuantityDrugById(drugId.drug_id);
        } catch (error) {
            console.error('Error getting drug quantity:', error);
            return res.status(500).json({ error: 'Failed to determine drug quantity' });
        }

        console.log(drugQty[0].qty);
        console.log(quantity);
        

        if (drugQty[0].qty >= quantity) {
            const price = await getDrugPriceById(drugId.drug_id);
            const totalPrice = price[0].price * quantity;
            totalAmount += totalPrice;
        } else {
            console.error('Not enough stock for drug:', name);
            return res.status(422).json({ error: `Not enough stock for ${name}` });
        }
    }

    try {
        // Step 1: Insert the transaction details into the `transactions` table
        const transactionResult = await addTransaction(visitId, totalAmount);

        // Step 2: For each drug in the transaction, insert the drug details into the `transaction_drugs` table
        const saleId = transactionResult[0].insertId;  // Assuming the `addTransaction` function returns the insertId
        for (const drug of drugs) {
            const { name, quantity } = drug;
            const drugId = await getDrugIdByName(name);

            await addTransactionDrug(saleId, drugId.drug_id, quantity);
            const addDrugsResult = await subtractDrugFromInventory(drugId.drug_id, quantity);
            console.log(drugId.drug_id);
            console.log(addDrugsResult);
        }

        return res.status(201).json({ message: 'Transaction and drug sales added successfully' });

    } catch (error) {
        console.error('Error inserting transaction and drugs sales:', error);
        return res.status(500).json({ error: 'Failed to add transaction and drug sales' });
    }
});


// Error handling middleware
app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).send('Something broke!');
});

// Start the server on port 8000
app.listen(8000, () => {
    console.log('Server is running on port 8000');
});