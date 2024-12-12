-- Populate the Users table
use nhs_recruitment;

insert into users (username, passwordHashed, firstName, lastName, role, lastLogin)
values
    ('admin', '$2a$12$diNTM8P5VDcw7jQ7bETrBOpkRNgTnHYp6BNM/Ea0OnzdQTBWLjD6G', 'Alice', 'Johnson', 'ROLE_ADMIN', NOW()),
    ('user', '$2a$12$diNTM8P5VDcw7jQ7bETrBOpkRNgTnHYp6BNM/Ea0OnzdQTBWLjD6G', 'Alice', 'Johnson', 'ROLE_USER', NOW()),
    ('user2', SHA2('password2', 256), 'Bob', 'Smith', 'ROLE_USER', NOW()),
    ('user3', SHA2('password3', 256), 'Carol', 'Williams', 'ROLE_USER', NOW()),
    ('user4', SHA2('password4', 256), 'David', 'Brown', 'ROLE_USER', NOW()),
    ('user5', SHA2('password5', 256), 'Eve', 'Davis', 'ROLE_USER', NOW()),
    ('user6', SHA2('password6', 256), 'Frank', 'Miller', 'ROLE_USER', NOW()),
    ('user7', SHA2('password7', 256), 'Grace', 'Wilson', 'ROLE_USER', NOW()),
    ('user8', SHA2('password8', 256), 'Henry', 'Moore', 'ROLE_USER', NOW()),
    ('user9', SHA2('password9', 256), 'Ivy', 'Taylor', 'ROLE_USER', NOW()),
    ('user10', SHA2('password10', 256), 'Jack', 'Anderson', 'ROLE_USER', NOW());


-- Populate the Applicants table with NHS-related data
insert into applicants (firstName, lastName, location, email, phoneNumber, eventAttended, skill)
values
    ('Sarah', 'Connor', 'Cardiff', 'kirill.akbulatov@pm.me', '1234567890', 'NHS Careers Fair', 'Healthcare, Nursing'),
    ('John', 'Doe', 'Swansea', 'kirill.spam1@gmail.com', '2345678901', 'NHS Recruitment Day', 'Healthcare, Public Health'),
    ('Jane', 'Smith', 'Newport', 'ayushyj04@gmail.com', '3456789012', 'Health Conference', 'Nursing, Midwifery'),
    ('Mike', 'Ross', 'Wrexham', 'mross|@example.com', '4567890123', 'NHS Recruitment', 'Clinical Research, Pharmacy'),
    ('Emily', 'Blunt', 'Bangor', '', '5678901234', 'Health Conference', 'Physiotherapy, Occupational Therapy'),
    ('Chris', 'Evans', 'Llandudno', 'cevans@example.com', '6789012345', 'NHS Careers Fair', 'Healthcare, Radiology'),
    ('Anna', 'Taylor', 'Aberystwyth', 'ataylor@example.com', '7890123456', 'Medical Career Day', 'Healthcare, Emergency Medicine'),
    ('Tom', 'Hanks', 'Carmarthen', 'thanks@example.com', '8901234567', 'NHS Recruitment', 'Healthcare, General Practice'),
    ('Sophia', 'Brown', 'Holyhead', 'sbrown@example.com', '9012345678', 'Medical Conference', 'Nursing, Geriatrics'),
    ('Max', 'Steel', 'Merthyr Tydfil', 'msteel@example.com', '1234567899', 'Health Careers Fair', 'Mental Health, Social Work'),
    ('Eva', 'Longoria', 'Bridgend', 'elongoria@example.com', '2345678902', 'Health Careers Event', 'Healthcare, Surgery'),
    ('Jake', 'Gyllenhaal', 'Pontypridd', 'jgyllenhaal@example.com', '3456789013', 'Medical Recruitment Day', 'Nursing, Emergency Care'),
    ('Liam', 'Hemsworth', 'Barry', 'lhemsworth@example.com', '4567890124', 'Health Careers Expo', 'Clinical Psychologist, Healthcare'),
    ('Emma', 'Stone', 'Barry', 'estone@example.com', '5678901235', 'NHS Recruitment Fair', 'Pediatrics, Nursing'),
    ('Olivia', 'Wilde', 'Caerphilly', 'owilde@example.com', '6789012346', 'Health Conference', 'Medical Research, Healthcare'),
    ('Noah', 'Centineo', 'Rhyl', 'ncentineo@example.com', '7890123457', 'NHS Career Expo', 'Pharmacy, Medical Laboratory Technician'),
    ('Lily', 'Collins', 'Abergavenny', 'lcollins@example.com', '8901234568', 'Healthcare Job Fair', 'Nursing, General Medicine'),
    ('Ethan', 'Hawke', 'Llanelli', 'ehawke@example.com', '9012345679', 'Health Expo', 'Healthcare, Radiography'),
    ('Ava', 'DuVernay', 'Conwy', 'aduvernay@example.com', '1234567898', 'NHS Careers Fair', 'Healthcare, Biomedical Science'),
    ('Jack', 'Black', 'Chepstow', 'jblack@example.com', '2345678903', 'Healthcare Recruitment Day', 'Surgery, Health Administration'),
    ('Isabella', 'Clark', 'Cardiff', 'isabella.clark@example.com', '3112234567', 'NHS Careers Expo', 'General Practice, Public Health'),
    ('Mason', 'Lewis', 'Swansea', 'mason.lewis@example.com', '4223345678', 'Health Careers Fair', 'Pharmacy, Radiology'),
    ('Mia', 'Anderson', 'Newport', 'mia.anderson@example.com', '5334456789', 'NHS Recruitment Day', 'Nursing, Pediatrics'),
    ('James', 'Walker', 'Wrexham', 'james.walker@example.com', '6445567890', 'Medical Careers Event', 'Biomedical Science, Surgery'),
    ('Sophia', 'Hall', 'Bangor', 'sophia.hall@example.com', '7556678901', 'Healthcare Job Fair', 'Mental Health, Physiotherapy'),
    ('William', 'Young', 'Llandudno', 'william.young@example.com', '8667789012', 'NHS Careers Day', 'Clinical Research, Occupational Therapy'),
    ('Ella', 'King', 'Aberystwyth', 'ella.king@example.com', '9778890123', 'Health Recruitment Event', 'Emergency Medicine, Nursing'),
    ('Lucas', 'Scott', 'Carmarthen', 'lucas.scott@example.com', '0889901234', 'NHS Careers Expo', 'Public Health, General Practice'),
    ('Charlotte', 'Green', 'Holyhead', 'charlotte.green@example.com', '1991012345', 'Medical Recruitment Fair', 'Surgery, Clinical Psychology'),
    ('Henry', 'White', 'Merthyr Tydfil', 'henry.white@example.com', '2112123456', 'Health Expo', 'Healthcare, Radiology'),
    ('Amelia', 'Moore', 'Bridgend', 'amelia.moore@example.com', '3223234567', 'Healthcare Careers Event', 'Pediatrics, Midwifery'),
    ('Oliver', 'Taylor', 'Pontypridd', 'oliver.taylor@example.com', '4334345678', 'NHS Careers Fair', 'Nursing, Social Work'),
    ('Evelyn', 'Harris', 'Barry', 'evelyn.harris@example.com', '5445456789', 'Medical Recruitment Day', 'Occupational Therapy, Physiotherapy'),
    ('Benjamin', 'Martin', 'Caerphilly', 'benjamin.martin@example.com', '6556567890', 'Health Careers Expo', 'Radiography, Mental Health'),
    ('Aria', 'Thompson', 'Rhyl', 'aria.thompson@example.com', '7667678901', 'NHS Recruitment Day', 'Pharmacy, Biomedical Science'),
    ('Alexander', 'Roberts', 'Abergavenny', 'alexander.roberts@example.com', '8778789012', 'Healthcare Careers Fair', 'Healthcare, Surgery'),
    ('Harper', 'Phillips', 'Llanelli', 'harper.phillips@example.com', '9889890123', 'Medical Careers Expo', 'Nursing, General Medicine'),
    ('Elijah', 'Campbell', 'Conwy', 'elijah.campbell@example.com', '0999901234', 'Health Recruitment Day', 'Clinical Research, Public Health'),
    ('Abigail', 'Parker', 'Chepstow', 'abigail.parker@example.com', '1011012345', 'NHS Careers Day', 'Emergency Medicine, Radiology'),
    ('Jacob', 'Evans', 'Cardiff', 'jacob.evans@example.com', '2122123456', 'Health Careers Fair', 'Healthcare, Midwifery'),
    ('Emily', 'Smith', 'Swansea', 'emily.smith@example.com', '3233234567', 'Medical Recruitment Fair', 'Nursing, Pediatrics'),
    ('Michael', 'Jones', 'Newport', 'michael.jones@example.com', '4344345678', 'NHS Careers Expo', 'General Practice, Biomedical Science'),
    ('Grace', 'Brown', 'Wrexham', 'grace.brown@example.com', '5455456789', 'Healthcare Careers Event', 'Radiography, Mental Health'),
    ('Daniel', 'Davis', 'Bangor', 'daniel.davis@example.com', '6566567890', 'Medical Careers Day', 'Surgery, Clinical Psychology'),
    ('Avery', 'Miller', 'Llandudno', 'avery.miller@example.com', '7677678901', 'NHS Careers Fair', 'Occupational Therapy, Nursing'),
    ('Matthew', 'Wilson', 'Aberystwyth', 'matthew.wilson@example.com', '8788789012', 'Health Careers Expo', 'Pharmacy, Emergency Medicine'),
    ('Ella', 'Clark', 'Carmarthen', 'ella.clark@example.com', '9899890123', 'Medical Recruitment Day', 'Midwifery, Pediatrics'),
    ('Ethan', 'Garcia', 'Holyhead', 'ethan.garcia@example.com', '0900901234', 'NHS Recruitment Day', 'Healthcare, Radiology'),
    ('Sofia', 'Martinez', 'Merthyr Tydfil', 'sofia.martinez@example.com', '1011012346', 'Health Recruitment Fair', 'Nursing, General Medicine'),
    ('James', 'Hernandez', 'Bridgend', 'james.hernandez@example.com', '2122123457', 'NHS Careers Event', 'Biomedical Science, Surgery'),
    ('Lily', 'Lopez', 'Pontypridd', 'lily.lopez@example.com', '3233234568', 'Healthcare Job Fair', 'Mental Health, Social Work'),
    ('Andrew', 'Lee', 'Barry', 'andrew.lee@example.com', '4344345679', 'Health Careers Day', 'Pharmacy, Emergency Medicine'),
    ('Victoria', 'Clarkson', 'Caerphilly', 'victoria.clarkson@example.com', '5455456780', 'NHS Recruitment Fair', 'General Practice, Pediatrics'),
    ('Samuel', 'Hill', 'Rhyl', 'samuel.hill@example.com', '6566567891', 'Medical Careers Expo', 'Nursing, Clinical Research'),
    ('Zoe', 'Hall', 'Abergavenny', 'zoe.hall@example.com', '7677678902', 'Health Expo', 'Physiotherapy, Occupational Therapy'),
    ('Ryan', 'Turner', 'Llanelli', 'ryan.turner@example.com', '8788789013', 'NHS Careers Fair', 'Radiology, Biomedical Science'),
    ('Natalie', 'Morgan', 'Conwy', 'natalie.morgan@example.com', '9899890124', 'Healthcare Careers Event', 'Emergency Medicine, Surgery'),
    ('Caleb', 'Reed', 'Chepstow', 'caleb.reed@example.com', '0900901235', 'Health Recruitment Day', 'Healthcare, Public Health'),
    ('Hazel', 'Baker', 'Cardiff', 'hazel.baker@example.com', '1011012347', 'Medical Careers Fair', 'Nursing, General Medicine'),
    ('Jason', 'Mitchell', 'Swansea', 'jason.mitchell@example.com', '2122123458', 'NHS Careers Expo', 'Occupational Therapy, Midwifery'),
    ('Hannah', 'Hughes', 'Newport', 'hannah.hughes@example.com', '3233234569', 'Health Careers Fair', 'Mental Health, Pediatrics'),
    ('Nathan', 'Carter', 'Wrexham', 'nathan.carter@example.com', '4344345680', 'Healthcare Job Fair', 'Radiography, Surgery'),
    ('Scarlett', 'Adams', 'Bangor', 'scarlett.adams@example.com', '5455456791', 'NHS Recruitment Day', 'Pharmacy, Nursing'),
    ('Christopher', 'Ward', 'Llandudno', 'christopher.ward@example.com', '6566567892', 'Health Careers Event', 'Biomedical Science, Emergency Medicine'),
    ('Amelia', 'Ramirez', 'Aberystwyth', 'amelia.ramirez@example.com', '7677678903', 'Medical Careers Expo', 'General Practice, Clinical Psychology'),
    ('Logan', 'Foster', 'Carmarthen', 'logan.foster@example.com', '8788789014', 'NHS Careers Day', 'Physiotherapy, Public Health'),
    ('Chloe', 'Diaz', 'Holyhead', 'chloe.diaz@example.com', '9899890125', 'Health Recruitment Event', 'Occupational Therapy, Radiology'),
    ('Dylan', 'Hayes', 'Merthyr Tydfil', 'dylan.hayes@example.com', '0900901236', 'Healthcare Careers Fair', 'Surgery, Nursing'),
    ('Aubrey', 'Brooks', 'Bridgend', 'aubrey.brooks@example.com', '1011012348', 'NHS Recruitment Fair', 'Midwifery, Pediatrics'),
    ('Adam', 'Kelly', 'Pontypridd', 'adam.kelly@example.com', '2122123459', 'Health Careers Expo', 'Clinical Research, Emergency Medicine'),
    ('Lillian', 'Sanders', 'Barry', 'lillian.sanders@example.com', '3233234570', 'Medical Careers Day', 'Radiology, General Medicine'),
    ('Cameron', 'Cooper', 'Caerphilly', 'cameron.cooper@example.com', '4344345681', 'NHS Careers Event', 'Nursing, Pharmacy'),
    ('Victoria', 'Murphy', 'Rhyl', 'victoria.murphy@example.com', '5455456792', 'Healthcare Careers Fair', 'Mental Health, Surgery'),
    ('Isaac', 'Rivera', 'Abergavenny', 'isaac.rivera@example.com', '6566567893', 'Health Recruitment Event', 'Public Health, Physiotherapy'),
    ('Lila', 'Flores', 'Llanelli', 'lila.flores@example.com', '7677678904', 'NHS Careers Day', 'Emergency Medicine, Biomedical Science'),
    ('Gavin', 'Powell', 'Conwy', 'gavin.powell@example.com', '8788789015', 'Medical Careers Expo', 'General Practice, Nursing'),
    ('Sophia', 'Cruz', 'Chepstow', 'sophia.cruz@example.com', '9899890126', 'Health Careers Day', 'Midwifery, Radiology'),
    ('Owen', 'Reed', 'Cardiff', 'owen.reed@example.com', '0900901237', 'NHS Recruitment Day', 'Occupational Therapy, Pediatrics'),
    ('Abigail', 'Barnes', 'Swansea', 'abigail.barnes@example.com', '1011012349', 'Healthcare Job Fair', 'Surgery, Clinical Psychology'),
    ('Jaxon', 'Ross', 'Newport', 'jaxon.ross@example.com', '2122123460', 'Medical Recruitment Fair', 'Pharmacy, Emergency Medicine'),
    ('Aurora', 'Patterson', 'Wrexham', 'aurora.patterson@example.com', '3233234571', 'NHS Careers Event', 'Biomedical Science, Public Health'),
    ('Wyatt', 'Ramirez', 'Bangor', 'wyatt.ramirez@example.com', '4344345682', 'Health Careers Expo', 'Radiology, Nursing'),
    ('Zoe', 'Griffin', 'Llandudno', 'zoe.griffin@example.com', '5455456793', 'Healthcare Careers Event', 'Mental Health, Surgery'),
    ('Eli', 'Gonzalez', 'Aberystwyth', 'eli.gonzalez@example.com', '6566567894', 'NHS Recruitment Fair', 'Pediatrics, Clinical Research'),
    ('Layla', 'Bryant', 'Carmarthen', 'layla.bryant@example.com', '7677678905', 'Health Recruitment Event', 'Midwifery, General Medicine'),
    ('Adrian', 'Alexander', 'Holyhead', 'adrian.alexander@example.com', '8788789016', 'NHS Careers Expo', 'Physiotherapy, Emergency Medicine'),
    ('Victoria', 'Coleman', 'Merthyr Tydfil', 'victoria.coleman@example.com', '9899890127', 'Healthcare Careers Fair', 'Nursing, Radiology');

-- Populate the Applicant Preferences table with sample data
insert into applicantPreferences (applicationId, SubscribeToNewsLetter, SubscribeToBulletins, SubscribeToJobUpdates)
values
    (1, 'no', 'no', 'yes'),
    (2, 'yes', 'yes', 'no'),
    (3, 'yes', 'yes', 'yes'),
    (4, 'no', 'yes', 'yes'),
    (5, 'no', 'yes', 'no'),
    (6, 'no', 'no', 'no'),
    (7, 'no', 'no', 'no'),
    (8, 'no', 'no', 'yes'),
    (9, 'no', 'yes', 'yes'),
    (10, 'no', 'no', 'no'),
    (11, 'no', 'yes', 'yes'),
    (12, 'no', 'yes', 'no'),
    (13, 'no', 'no', 'yes'),
    (14, 'no', 'yes', 'yes'),
    (15, 'no', 'yes', 'no'),
    (16, 'no', 'yes', 'yes'),
    (17, 'no', 'no', 'yes'),
    (18, 'no', 'no', 'yes'),
    (19, 'no', 'yes', 'yes'),
    (20, 'no', 'yes', 'no'),
    (21, 'no', 'no', 'yes'),
    (22, 'no', 'yes', 'no'),
    (23, 'no', 'yes', 'yes'),
    (24, 'no', 'no', 'yes'),
    (25, 'no', 'yes', 'yes'),
    (26, 'no', 'no', 'no'),
    (27, 'no', 'yes', 'no'),
    (28, 'no', 'yes', 'yes'),
    (29, 'no', 'no', 'yes'),
    (30, 'no', 'no', 'no'),
    (31, 'no', 'yes', 'yes'),
    (32, 'no', 'yes', 'no'),
    (33, 'no', 'yes', 'yes'),
    (34, 'no', 'no', 'yes'),
    (35, 'no', 'no', 'yes'),
    (36, 'no', 'yes', 'no'),
    (37, 'no', 'no', 'yes'),
    (38, 'no', 'yes', 'yes'),
    (39, 'no', 'yes', 'yes'),
    (40, 'no', 'no', 'no'),
    (41, 'no', 'yes', 'no'),
    (42, 'no', 'yes', 'yes'),
    (43, 'no', 'no', 'yes'),
    (44, 'no', 'no', 'no'),
    (45, 'no', 'yes', 'yes'),
    (46, 'no', 'yes', 'no'),
    (47, 'no', 'no', 'yes'),
    (48, 'no', 'yes', 'yes'),
    (49, 'no', 'no', 'yes'),
    (50, 'no', 'yes', 'no'),
    (51, 'no', 'yes', 'no'),
    (52, 'no', 'no', 'yes'),
    (53, 'no', 'no', 'yes'),
    (54, 'no', 'yes', 'yes'),
    (55, 'no', 'yes', 'no'),
    (56, 'no', 'yes', 'yes'),
    (57, 'no', 'no', 'yes'),
    (58, 'no', 'yes', 'no'),
    (59, 'no', 'no', 'yes'),
    (60, 'no', 'yes', 'no'),
    (61, 'no', 'yes', 'yes'),
    (62, 'no', 'no', 'yes'),
    (63, 'no', 'yes', 'no'),
    (64, 'no', 'yes', 'no'),
    (65, 'no', 'no', 'yes'),
    (66, 'no', 'yes', 'yes'),
    (67, 'no', 'yes', 'yes'),
    (68, 'no', 'no', 'no'),
    (69, 'no', 'yes', 'yes'),
    (70, 'no', 'yes', 'no'),
    (71, 'no', 'no', 'yes'),
    (72, 'no', 'yes', 'no'),
    (73, 'no', 'yes', 'yes'),
    (74, 'no', 'no', 'no'),
    (75, 'no', 'yes', 'yes'),
    (76, 'no', 'yes', 'no'),
    (77, 'no', 'no', 'yes'),
    (78, 'no', 'no', 'yes'),
    (79, 'no', 'yes', 'no'),
    (80, 'no', 'yes', 'yes'),
    (81, 'no', 'no', 'yes'),
    (82, 'no', 'yes', 'no'),
    (83, 'no', 'no', 'yes'),
    (84, 'no', 'yes', 'yes'),
    (85, 'no', 'no', 'no'),
    (86, 'no', 'yes', 'yes'),
    (87, 'no', 'yes', 'yes');

-- Populate the Application Details table
insert into applicationdetails(applicationId, currentPosition, status, CvPath, CoverLetterPath)
values
    (1, 'Nurse', 'External', NULL, NULL),
    (2, 'Public Health Analyst', 'External', NULL, NULL),
    (3, 'Midwife', 'Internal', NULL, NULL),
    (4, 'Clinical Researcher', 'External', NULL, NULL),
    (5, 'Physiotherapist', 'Internal', NULL, NULL),
    (6, 'Radiology Technician', 'External', NULL, NULL),
    (7, 'Emergency Medicine Doctor', 'External', NULL, NULL),
    (8, 'General Practitioner', 'External', NULL, NULL),
    (9, 'Geriatric Nurse', 'Internal', NULL, NULL),
    (10, 'Mental Health Counselor', 'External', NULL, NULL),
    (11, 'Pharmacist', 'Internal', NULL, NULL),
    (12, 'Medical Lab Technician', 'External', NULL, NULL),
    (13, 'Pediatric Nurse', 'Internal', NULL, NULL),
    (14, 'Medical Researcher', 'External', NULL, NULL),
    (15, 'Surgical Nurse', 'External', NULL, NULL),
    (16, 'Radiographer', 'Internal', NULL, NULL),
    (17, 'Biomedical Scientist', 'External', NULL, NULL),
    (18, 'Surgical Assistant', 'External', NULL, NULL),
    (19, 'Healthcare Administrator', 'Internal', NULL, NULL),
    (20, 'Health Coordinator', 'External', NULL, NULL),
    (21, 'Healthcare Analyst', 'Internal', NULL, NULL),
    (22, 'Midwife', 'External', NULL, NULL),
    (23, 'Clinical Researcher', 'Internal', NULL, NULL),
    (24, 'Pharmacist', 'External', NULL, NULL),
    (25, 'Radiology Technician', 'Internal', NULL, NULL),
    (26, 'Emergency Doctor', 'External', NULL, NULL),
    (27, 'General Practitioner', 'External', NULL, NULL),
    (28, 'Pediatrics Nurse', 'Internal', NULL, NULL),
    (29, 'Medical Lab Technician', 'External', NULL, NULL),
    (30, 'Healthcare Administrator', 'Internal', NULL, NULL),
    (31, 'Radiographer', 'External', NULL, NULL),
    (32, 'Surgical Assistant', 'Internal', NULL, NULL),
    (33, 'Public Health Analyst', 'External', NULL, NULL),
    (34, 'Mental Health Specialist', 'Internal', NULL, NULL),
    (35, 'Biomedical Scientist', 'External', NULL, NULL),
    (36, 'Healthcare Manager', 'Internal', NULL, NULL),
    (37, 'Clinical Researcher', 'External', NULL, NULL),
    (38, 'Surgical Nurse', 'Internal', NULL, NULL),
    (39, 'Pharmacist', 'External', NULL, NULL),
    (40, 'Emergency Nurse', 'Internal', NULL, NULL),
    (41, 'Mental Health Nurse', 'External', NULL, NULL),
    (42, 'Radiology Technician', 'Internal', NULL, NULL),
    (43, 'General Practitioner', 'External', NULL, NULL),
    (44, 'Pediatrics Specialist', 'Internal', NULL, NULL),
    (45, 'Healthcare Manager', 'External', NULL, NULL),
    (46, 'Public Health Specialist', 'Internal', NULL, NULL),
    (47, 'Radiology Technician', 'External', NULL, NULL),
    (48, 'Clinical Psychologist', 'Internal', NULL, NULL),
    (49, 'Surgical Nurse', 'External', NULL, NULL),
    (50, 'Healthcare Analyst', 'Internal', NULL, NULL),
    (51, 'Emergency Care Specialist', 'External', NULL, NULL),
    (52, 'Midwife', 'Internal', NULL, NULL),
    (53, 'Medical Researcher', 'External', NULL, NULL),
    (54, 'Healthcare Coordinator', 'Internal', NULL, NULL),
    (55, 'Radiographer', 'External', NULL, NULL),
    (56, 'Biomedical Scientist', 'Internal', NULL, NULL),
    (57, 'Emergency Nurse', 'External', NULL, NULL),
    (58, 'Pharmacist', 'Internal', NULL, NULL),
    (59, 'Mental Health Nurse', 'External', NULL, NULL),
    (60, 'General Practitioner', 'Internal', NULL, NULL),
    (61, 'Healthcare Specialist', 'External', NULL, NULL),
    (62, 'Clinical Researcher', 'Internal', NULL, NULL),
    (63, 'Radiology Specialist', 'External', NULL, NULL),
    (64, 'Public Health Analyst', 'Internal', NULL, NULL),
    (65, 'Midwife', 'External', NULL, NULL),
    (66, 'Medical Researcher', 'Internal', NULL, NULL),
    (67, 'Pediatrics Nurse', 'External', NULL, NULL),
    (68, 'Emergency Specialist', 'Internal', NULL, NULL),
    (69, 'Surgical Assistant', 'External', NULL, NULL),
    (70, 'Healthcare Manager', 'Internal', NULL, NULL),
    (71, 'Mental Health Specialist', 'External', NULL, NULL),
    (72, 'Radiology Technician', 'Internal', NULL, NULL),
    (73, 'General Practitioner', 'External', NULL, NULL),
    (74, 'Clinical Psychologist', 'Internal', NULL, NULL),
    (75, 'Healthcare Manager', 'External', NULL, NULL),
    (76, 'Pediatrics Specialist', 'Internal', NULL, NULL),
    (77, 'Radiographer', 'External', NULL, NULL),
    (78, 'Emergency Nurse', 'Internal', NULL, NULL),
    (79, 'Biomedical Scientist', 'External', NULL, NULL),
    (80, 'Public Health Specialist', 'Internal', NULL, NULL),
    (81, 'Medical Lab Technician', 'External', NULL, NULL),
    (82, 'Clinical Researcher', 'Internal', NULL, NULL),
    (83, 'Healthcare Manager', 'External', NULL, NULL),
    (84, 'Radiology Technician', 'Internal', NULL, NULL),
    (85, 'Mental Health Nurse', 'External', NULL, NULL),
    (86, 'Surgical Specialist', 'Internal', NULL, NULL),
    (87, 'Pharmacist', 'External', NULL, NULL);


-- Populate the communicationLogs table
insert into communicationlogs (applicantId, userId, timestamp, userType, logType, communicationType, actionTaken, notes)
VALUES
    (1, 2, DATE_SUB(NOW(), INTERVAL 10 DAY), 'recruiter', 'communication', 'email', 'emailSent', 'Invitation to interview'),
    (1, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'email', 'applicantDetailsChanged', 'changed email address'),
    (1, 3, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'email', 'emailSent', 'Invitation to interview'),
    (1, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'phone', 'emailSent', 'Discussed application details'),
    (1, 3, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'email', 'applicantDetailsChanged', 'Updated phone number'),
    (1, 4, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'text', 'applicantAdded', 'Sent confirmation of registration'),
    (1, 5, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'person', 'applicantDetailsChanged', 'Changed event attended information'),
    (1, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'phone', 'note', 'Provided additional instructions for interview'),
    (1, 3, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'person', 'applicantRemoved', 'Discussed withdrawal of application'),
    (2, 4, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'email', 'applicantDetailsChanged', 'New applicant profile created'),
    (2, 5, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'text', 'emailSent', 'Sent final offer email'),
    (2, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'person', 'applicantDetailsChanged', 'Updated applicant skills'),
    (3, 3, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'email', 'note', 'Notified applicant about next steps'),
    (4, 4, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'phone', 'applicantAdded', 'Applicant confirmed availability for interview'),
    (5, 5, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'person', 'emailSent', 'Shared job description details'),
    (6, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'email', 'note', 'Added a note regarding applicant follow-up'),
    (7, 3, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'phone', 'applicantRemoved', 'Applicant declined job offer'),
    (8, 4, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'text', 'emailSent', 'Reminder for upcoming assessment center'),
    (9, 5, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'person', 'applicantDetailsChanged', 'Corrected spelling in applicant name'),
    (10, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'person', 'emailSent', 'In-person interview invitation sent');


-- populate the systemLogs table
INSERT INTO systemLogs (systemLogId, userId, actionTaken, timestamp)
VALUES
    (1,2, 'login',DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) DAY)),
    (2,3, 'login', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (3,4, 'login', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (4,5, 'login', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (5,6, 'login', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (6,7, 'login', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (7,8, 'login', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (8,9, 'login', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (9,10, 'login', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (10,2, 'logout', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (11,3, 'logout', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (12,4, 'logout', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (13,5, 'logout', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (14,6, 'logout', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (15,7, 'logout', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (16,8, 'logout', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (17,9, 'logout', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR)),
    (18,10, 'logout', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR));




DESCRIBE users;