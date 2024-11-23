import mysql from 'mysql2';
import dotenv from 'dotenv';

dotenv.config();

const pool = mysql.createPool({
    host: process.env.MYSQL_HOST,
    user: process.env.MYSQL_USER,
    password: process.env.MYSQL_PASSWORD,
    database: process.env.MYSQL_DATABASE,
}).promise();

// Helper function to get all patients
export async function getPatients() {
    const [rows] = await pool.query("SELECT patient_id, last_name, first_name, middle_initial, gender, DATE_FORMAT(birthdate, '%Y-%m-%d') AS birthdate, mobile_number, email_address FROM patients");
    return rows;
}

// Get a specific patient by ID
export async function getPatient(id) {
    const [rows] = await pool.query(`
        SELECT * 
        FROM patients 
        WHERE patient_id = ?
    `, [id]);
    return rows;
}

// Add a new patient
export async function addPatient(last_name, first_name, gender, birthdate, mobile_number, email_address) {
    const [result] = await pool.query(`
        INSERT INTO patients (last_name, first_name, gender, birthdate, mobile_number, email_address)
        VALUES (?, ?, ?, ?, ?, ?)
    `, [last_name, first_name, gender, birthdate, mobile_number, email_address]);
    const id = result.insertId;
    return getPatient(id);
}

// Delete a patient by ID
export async function deletePatient(id) {
    const [result] = await pool.query(`
        DELETE 
        FROM patients 
        WHERE patient_id = ?
    `, [id]);
    return result;
}

// Get patients by name
export async function getPatientsByName(firstName, lastName) {
    const sql = `SELECT patient_id, last_name, first_name, middle_initial, gender, DATE_FORMAT(birthdate, '%Y-%m-%d') AS birthdate, mobile_number, email_address
     FROM patients WHERE first_name = ? AND last_name = ?`;
    try {
        const [rows] = await pool.query(sql, [firstName, lastName]);
        return rows;
    } catch (error) {
        console.error('Error executing query:', error);
        throw error;
    }
}

// Get ailment ID based on illness name
export async function getAilmentIdByName(name) {
    const sql = 'SELECT ailment_id, speci_id FROM REF_ailments WHERE name = ? LIMIT 1';
    const [rows] = await pool.query(sql, [name]);

    if (rows.length > 0) {
        return rows[0]; // Return the first matching result
    } else {
        return null; // No matching ailment found
    }
}

// Get all ailments
export async function getAllAilments() {
    const sql = 'SELECT * FROM REF_ailments';  // Assuming you have an 'ailments' table
    const [rows] = await pool.query(sql);
    return rows;
}

// Get all visits
export async function getAllVisits() {
    const sql = 'SELECT * FROM visits';  // Assuming you have a 'visits' table
    const [rows] = await pool.query(sql);
    return rows;
}

// Add a visit
export async function addVisit(patient_id, log_in, ailment_id, doctor_id) {
    const [result] = await pool.query(`
        INSERT INTO visits (patient_id, log_in, ailment_id, doctor_id)
        VALUES (?, ?, ?, ?)
    `, [patient_id, log_in, ailment_id, doctor_id])

    return result;
};


// NEW: Get doctors by ailment specialization (speci_id)
export async function getDoctorsForAilment(speci_id) {
    const sql = `
        SELECT doctors.doctor_id, doctors.last_name, doctors.first_name, doctors.middle_initial, doctors.consultation_rate
        FROM doctors
        JOIN doctor_speci ON doctor_speci.doctor_id = doctors.doctor_id
        WHERE doctor_speci.speci_id = ?
    `;
    
    const [rows] = await pool.query(sql, [speci_id]);
    return rows;  // Returns a list of doctors' names
}

export async function getVisitDetails(patientId) {
    const sql = `
        SELECT v.visit_id, DATE_FORMAT(v.log_in, '%Y-%m-%d %H:%i:%s') AS log_in, r.name
        FROM visits v
        JOIN REF_ailments r ON v.ailment_id = r.ailment_id
        WHERE v.patient_id = ?;
    `;

    const [rows] = await pool.execute(sql, [patientId]);
    return rows; // Returns an array of visit details with ailment names
};

export async function getPrescribedDrugs(visitId) {
    const query = `
        SELECT pd.drug_id, d.brand_name
        FROM prescribed_drugs pd
        JOIN drugs d ON pd.drug_id = d.drug_id
        WHERE pd.visit_id = ?
    `;

    try {
        const [rows] = await pool.execute(query, [visitId]);
        return rows; // Return the rows directly
    } catch (error) {
        console.error('Error fetching prescribed drugs:', error);
        throw new Error('Failed to fetch prescribed drugs');
    }
};

export async function checkVisitExists(visit_id) {
    const [rows] = await pool.query(
        `SELECT * FROM visits WHERE visit_id = ?`, 
        [visit_id]
    );
    return rows.length > 0; // Returns true if the visit exists
}

// Fetch drug_id by drug_name
export async function getDrugIdByName(drug_name) {
    const [rows] = await pool.query(
        `SELECT drug_id FROM drugs WHERE brand_name = ? LIMIT 1`, 
        [drug_name]
    );
    return rows[0]; // Returns the first matching result (or undefined if not found)
}

// Insert into prescribed_drugs
export async function prescribeDrug(visit_id, drug_id, amount) {
    const [result] = await pool.query(
        `INSERT INTO prescribed_drugs (visit_id, drug_id, qty_drugs) VALUES (?, ?, ?)`, 
        [visit_id, drug_id, amount]
    );
    return result; // Returns the query result (e.g., insertId)
}

export async function getLowDrugs() {
    const [result] = await pool.query(
        `SELECT inventory.drug_id, drugs.brand_name, qty
        FROM inventory
        JOIN drugs ON inventory.drug_id = drugs.drug_id
        WHERE qty < 50`
    );

    return result;
}

export async function getDrugIdFromName(drug_name) {
    const [result] = await pool.query(
        `SELECT drug_id
        FROM drugs
        WHERE drug_name = ?
    `, [drug_name])

    return result;
}

export async function addShipment(shipmentDate, shipmentCost) {
    const query = `INSERT INTO shipments (date, shipment_cost) VALUES (?, ?)`;
    const values = [shipmentDate, shipmentCost];
    const result = await pool.query(query, values);
    return result;  // Return the result, which should include insertId
}

export async function addShipmentDrug(shipmentId, drugId, drugQuantity, drugPrice) {
    const query = `INSERT INTO shipment_drug (shipment_id, drug_id, qty) VALUES (?, ?, ?)`;
    const values = [shipmentId, drugId, drugQuantity];
    await pool.query(query, values);  // Execute the query
}

export async function addDrugToInventory(drugId, drugQuantity) {
    const [result] = await pool.query(
        `UPDATE inventory
        SET qty = qty + ?
        WHERE drug_id = ?
    `, [drugQuantity, drugId]);

    return result;
}

export async function getQuantityDrugById(drugId) {
    const [result] = await pool.query(
        `SELECT qty
        FROM inventory
        WHERE drug_id = ?
    `, [drugId])
    
    return result;
}

export async function addTransaction(visitId, amt_paid) {
    const query = `INSERT INTO sales (visit_id, amt_paid) VALUES (?, ?)`;
    const values = [visitId, amt_paid];
    const result = await pool.query(query, values);
    return result;  // Return the result, which should include insertId
}

export async function addTransactionDrug(saleId, drugId, drugQuantity) {
    const query = `INSERT INTO drugs_sold (sale_id, drug_id, qty) VALUES (?, ?, ?)`;
    const values = [saleId, drugId, drugQuantity];
    await pool.query(query, values);  // Execute the query
}

export async function getDrugPriceById(drugId) {
    const [result] = await pool.query(
        `SELECT price
        FROM drugs
        WHERE drug_id = ?
    `, [drugId])

    console.log(result);
    return result;
}

export async function subtractDrugFromInventory(drugId, quantity) {
    const [result] = await pool.query(
        `UPDATE inventory
        SET qty = qty - ?
        WHERE drug_id = ?
    `, [quantity, drugId]);

    return result;
}

export async function getDoctorRateFromId(doctorId) {
    const [result] = await pool.query(
        `SELECT consultation_rate
        FROM doctors
        WHERE doctor_id = ?
    `, [doctorId]);

    return result;
}

export async function getAllDrugs() {
    const [result] = await pool.query(
        `SELECT inventory.drug_id, drugs.brand_name, qty
        FROM inventory
        JOIN drugs ON inventory.drug_id = drugs.drug_id
    `)

    return result;
}