-- Insert random doctors
ALTER TABLE clinic.doctors AUTO_INCREMENT = 1;
INSERT INTO clinic.doctors (last_name, middle_initial, first_name, gender, birthdate, consultation_rate, mobile_number, email_address) VALUES
('Smith', 'J', 'John', 'M', '1980-05-12', 500.00, 639171234567, 'john.smith@example.com'),
('Doe', 'L', 'Jane', 'F', '1985-08-22', 700.00, 639181234567, 'jane.doe@example.com'),
('Brown', 'K', 'Alice', 'F', '1990-01-15', 600.00, 639191234567, 'alice.brown@example.com'),
('Taylor', 'M', 'Michael', 'M', '1975-03-20', 800.00, 639201234567, 'michael.taylor@example.com'),
('Wilson', 'N', 'Nancy', 'F', '1987-07-09', 750.00, 639211234567, 'nancy.wilson@example.com');

INSERT INTO clinic.patients (patient_id, last_name, first_name, middle_initial, gender, birthdate, mobile_number, email_address) VALUES
(1, 'Doe', 'John', 'A', 'M', '1998-05-10', '09171234567', 'john.doe@example.com'),
(2, 'Smith', 'Jane', 'B', 'F', '2000-02-25', '09231234567', 'jane.smith@example.com'),
(3, 'Johnson', 'Michael', 'C', 'M', '1995-12-01', '09341234567', 'michael.johnson@example.com'),
(4, 'Williams', 'Emily', 'D', 'F', '1997-03-14', '09451234567', 'emily.williams@example.com'),
(5, 'Brown', 'David', 'E', 'M', '1993-08-20', '09561234567', 'david.brown@example.com'),
(6, 'Davis', 'Sarah', 'F', 'F', '2002-06-30', '09671234567', 'sarah.davis@example.com'),
(7, 'Miller', 'James', 'G', 'M', '2000-11-15', '09781234567', 'james.miller@example.com'),
(8, 'Wilson', 'Jessica', 'H', 'F', '1996-09-25', '09891234567', 'jessica.wilson@example.com'),
(9, 'Moore', 'William', 'I', 'M', '1999-04-07', '09901234567', 'william.moore@example.com'),
(10, 'Taylor', 'Olivia', 'J', 'F', '1994-01-22', '09112345678', 'olivia.taylor@example.com'),
(11, 'Anderson', 'Matthew', 'K', 'M', '2001-10-05', '09222345678', 'matthew.anderson@example.com'),
(12, 'Thomas', 'Sophia', 'L', 'F', '1997-07-10', '09332345678', 'sophia.thomas@example.com'),
(13, 'Jackson', 'Daniel', 'M', 'M', '1995-05-17', '09442345678', 'daniel.jackson@example.com'),
(14, 'White', 'Charlotte', 'N', 'F', '1998-12-30', '09552345678', 'charlotte.white@example.com'),
(15, 'Harris', 'Lucas', 'O', 'M', '2000-03-18', '09662345678', 'lucas.harris@example.com');

INSERT INTO clinic.visits (visit_id, patient_id, log_in, ailment_id, doctor_id) VALUES
(1, 1, '2023-01-15 08:30:00', 1, 1),
(2, 2, '2023-02-20 09:00:00', 5, 2),
(3, 3, '2023-03-10 10:15:00', 6, 3),
(4, 4, '2023-04-05 11:00:00', 8, 4),
(5, 5, '2023-05-12 08:45:00', 11, 5),
(6, 6, '2023-06-07 10:30:00', 4, 1),
(7, 7, '2023-07-13 14:00:00', 5, 2),
(8, 8, '2023-08-22 13:15:00', 7, 3),
(9, 9, '2023-09-19 09:30:00', 8, 4),
(10, 10, '2023-10-10 15:00:00', 11, 5),
(11, 11, '2023-11-03 16:45:00', 1, 1),
(12, 12, '2023-11-17 11:30:00', 5, 2),
(13, 13, '2023-12-01 12:00:00', 6, 3),
(14, 14, '2023-12-15 08:00:00', 9, 4),
(15, 15, '2024-01-07 10:30:00', 13, 5),
(16, 1, '2024-01-01 20:30:00', 12, 5),
(17, 1, '2024-01-14 13:30:00', 15, 5),
(18, 3, '2024-01-23 09:30:00', 14, 5),
(19, 4, '2024-01-20 11:30:00', 5, 2),
(20, 5, '2024-01-02 12:30:00', 6, 3);

INSERT INTO clinic.shipments (shipment_id, date, shipment_cost) VALUES
(1, '2023-01-15', 4800.00),
(2, '2023-03-22', 5200.00),
(3, '2023-06-05', 6000.00),
(4, '2023-08-18', 5300.00),
(5, '2023-11-12', 7500.00),
(6, '2024-02-15', 5000.00),
(7, '2024-03-25', 4900.00),
(8, '2024-05-10', 7200.00),
(9, '2024-07-19', 6700.00),
(10, '2024-09-21', 6800.00),
(11, '2024-10-15', 5600.00),
(12, '2024-11-01', 4500.00);

INSERT INTO clinic.shipment_drug (shipment_id, drug_id, qty) VALUES
-- Shipment 1 (2023-01-15)
(1, 1, 50), -- Eczacort (Hydrocortisone) @ 332.5
(1, 3, 300), -- Allerta (Loratadine) @ 26.08
(1, 7, 400), -- Diatabs (Loperamide) @ 8.5

-- Shipment 2 (2023-03-22)
(2, 5, 250), -- Alnix (Cetirizine) @ 33.47
(2, 10, 500), -- Neozep Forte @ 7.00
(2, 14, 200), -- Advil (Ibuprofen) @ 9.00

-- Shipment 3 (2023-06-05)
(3, 2, 100), -- Histacort (Prednisolone) @ 202.5
(3, 9, 300), -- Skelan (Naproxen Sodium) @ 9.50
(3, 12, 400), -- Bioflu (Phenylephrine HCl, etc.) @ 9.00

-- Shipment 4 (2023-08-18)
(4, 4, 400), -- Claritin (Loratadine) @ 37.00
(4, 6, 300), -- Lactezin (Lactoferrin) @ 33.00
(4, 13, 150), -- Dizitab (Meclizine HCl) @ 11.00

-- Shipment 5 (2023-11-12)
(5, 8, 500), -- Kremil S @ 9.75
(5, 11, 600), -- Biogesic (Paracetamol) @ 4.75
(5, 1, 50), -- Eczacort (Hydrocortisone) @ 332.5

-- Shipment 6 (2024-02-15)
(6, 2, 200), -- Histacort (Prednisolone) @ 202.5
(6, 5, 250), -- Alnix (Cetirizine) @ 33.47
(6, 9, 300), -- Skelan (Naproxen Sodium) @ 9.50

-- Shipment 7 (2024-03-25)
(7, 3, 300), -- Allerta (Loratadine) @ 26.08
(7, 10, 400), -- Neozep Forte @ 7.00
(7, 14, 200), -- Advil (Ibuprofen) @ 9.00

-- Shipment 8 (2024-05-10)
(8, 6, 500), -- Lactezin (Lactoferrin) @ 33.00
(8, 8, 600), -- Kremil S @ 9.75
(8, 12, 400), -- Bioflu (Phenylephrine HCl, etc.) @ 9.00

-- Shipment 9 (2024-07-19)
(9, 4, 300), -- Claritin (Loratadine) @ 37.00
(9, 7, 400), -- Diatabs (Loperamide) @ 8.5
(9, 11, 600), -- Biogesic (Paracetamol) @ 4.75

-- Shipment 10 (2024-09-21)
(10, 2, 100), -- Histacort (Prednisolone) @ 202.5
(10, 13, 200), -- Dizitab (Meclizine HCl) @ 11.00
(10, 9, 400), -- Skelan (Naproxen Sodium) @ 9.50

-- Shipment 11 (2024-10-15)
(11, 1, 50), -- Eczacort (Hydrocortisone) @ 332.5
(11, 5, 250), -- Alnix (Cetirizine) @ 33.47
(11, 14, 200), -- Advil (Ibuprofen) @ 9.00

-- Shipment 12 (2024-11-01)
(12, 3, 400), -- Allerta (Loratadine) @ 26.08
(12, 8, 500), -- Kremil S @ 9.75
(12, 12, 300); -- Bioflu (Phenylephrine HCl, etc.) @ 9.00

INSERT INTO clinic.sales (sale_id, visit_id, amt_paid) VALUES
(1, 1, 546.5),
(2, 2, 578.5),
(3, 3, 430.5),
(4, 4, 334.5),
(5, 5, 505.5),
(6, 6, 652.5),
(7, 7, 287.5),
(8, 8, 478.0),
(9, 9, 595.5),
(10, 10, 310.0),
-- Sale of Drugs
(11, 1, 7647.50),
(12, 2, 332.50),
(13, 3, 52.16),
(14, 4, 185.00),
(15, 15, 334.7),
(16, 16, 334.7),
(17, 17, 769.81),
(18, 18, 736.34),
(19, 19, 180.50),
(20, 20, 217.00);

INSERT INTO clinic.drugs_sold (sale_id, drug_id, qty) VALUES
(11, 1, 23),
(12, 1, 1),
(13, 3, 2),
(14, 4, 5),
(15, 5, 10),
(16, 5, 10),
(17, 5, 23),
(18, 5, 22),
(19, 9, 19),
(20, 10, 31);

INSERT INTO clinic.inventory (drug_id, qty) VALUES
(1, 50),
(3, 150),
(4, 500),
(5, 1023),
(9, 192),
(10, 311);

INSERT INTO clinic.doctor_speci (doctor_id, speci_id) VALUES
('1', '1'),
('2', '2'),
('3', '3'),
('4', '4'),
('5', '5');

INSERT INTO clinic.prescribed_drugs (visit_id, drug_id, qty_drugs) VALUES
(1, 1, 23),
(2, 1, 1),
(3, 3, 2),
(4, 4, 5),
(15, 5, 10),
(16, 5, 10),
(17, 5, 23),
(18, 5, 22),
(19, 9, 19),
(20, 10, 31);
