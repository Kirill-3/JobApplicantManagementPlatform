-- Populate the Users table
insert into users (username, passwordHashed, firstName, lastName, role, lastLogin)
values
    ('admin', SHA2('password1', 256), 'Alice', 'Johnson', 'admin', NOW()),
    ('user1', SHA2('password1', 256), 'Alice', 'Johnson', 'recruiter', NOW()),
    ('user2', SHA2('password2', 256), 'Bob', 'Smith', 'recruiter', NOW()),
    ('user3', SHA2('password3', 256), 'Carol', 'Williams', 'recruiter', NOW()),
    ('user4', SHA2('password4', 256), 'David', 'Brown', 'recruiter', NOW()),
    ('user5', SHA2('password5', 256), 'Eve', 'Davis', 'recruiter', NOW()),
    ('user6', SHA2('password6', 256), 'Frank', 'Miller', 'recruiter', NOW()),
    ('user7', SHA2('password7', 256), 'Grace', 'Wilson', 'recruiter', NOW()),
    ('user8', SHA2('password8', 256), 'Henry', 'Moore', 'recruiter', NOW()),
    ('user9', SHA2('password9', 256), 'Ivy', 'Taylor', 'recruiter', NOW()),
    ('user10', SHA2('password10', 256), 'Jack', 'Anderson', 'recruiter', NOW());


-- Populate the Applicants table with NHS-related data
insert into applicants (firstName, lastName, location, email, phoneNumber, eventAttended, skill)
values
    ('Sarah', 'Connor', 'Cardiff', 'kirill.akbulatov@pm.me', '1234567890', 'NHS Careers Fair', 'Healthcare, Nursing'),
    ('John', 'Doe', 'Swansea', 'kirill.spam1@gmail.com', '2345678901', 'NHS Recruitment Day', 'Healthcare, Public Health'),
    ('Jane', 'Smith', 'Newport', '', '3456789012', 'Health Conference', 'Nursing, Midwifery'),
    ('Mike', 'Ross', 'Wrexham', 'mross|@example.com', '4567890123', 'NHS Recruitment', 'Clinical Research, Pharmacy'),
    ('Emily', 'Blunt', 'Bangor', 'eblunt@example.com', '5678901234', 'Health Conference', 'Physiotherapy, Occupational Therapy'),
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
    ('Jack', 'Black', 'Chepstow', 'jblack@example.com', '2345678903', 'Healthcare Recruitment Day', 'Surgery, Health Administration');

-- Populate the Applicant Preferences table with sample data
insert into applicantPreferences (applicationId, SubscribeToNewsLetter, SubscribeToBulletins, SubscribeToJobUpdates)
values
    (1, 'yes', 'no', 'yes'),
    (2, 'yes', 'yes', 'no'),
    (3, 'no', 'yes', 'yes'),
    (4, 'yes', 'yes', 'yes'),
    (5, 'no', 'yes', 'no'),
    (6, 'yes', 'no', 'no'),
    (7, 'no', 'no', 'no'),
    (8, 'yes', 'no', 'yes'),
    (9, 'yes', 'yes', 'yes'),
    (10, 'yes', 'no', 'no'),
    (11, 'yes', 'yes', 'yes'),
    (12, 'no', 'yes', 'no'),
    (13, 'yes', 'no', 'yes'),
    (14, 'yes', 'yes', 'yes'),
    (15, 'no', 'yes', 'no'),
    (16, 'yes', 'yes', 'yes'),
    (17, 'no', 'no', 'yes'),
    (18, 'yes', 'no', 'yes'),
    (19, 'no', 'yes', 'yes'),
    (20, 'yes', 'yes', 'no');

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
    (20, 'Health Coordinator', 'External', NULL, NULL);


-- Populate the communicationLogs table
insert into communicationlogs (applicantId, userId, timestamp, userType, logType, communicationType, actionTaken, notes)
VALUES
    (1, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR) , 'recruiter', 'communication', 'email', 'emailSent', 'Invitation to interview'),
    (1, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'email', 'applicantDetailsChanged', 'changed email address'),
    (1, 3, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'email', 'emailSent', 'Invitation to interview'),
    (1, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'phone', 'emailSent', 'Discussed application details'),
    (1, 3, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'email', 'applicantDetailsChanged', 'Updated phone number'),
    (1, 4, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'text', 'applicantAdded', 'Sent confirmation of registration'),
    (1, 5, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'person', 'applicantDetailsChanged', 'Changed event attended information'),
    (1, 2, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'phone', 'note', 'Provided additional instructions for interview'),
    (1, 3, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'communication', 'person', 'applicantRemoved', 'Discussed withdrawal of application'),
    (2, 4, DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 48) HOUR), 'recruiter', 'detailChange', 'email', 'addedUser', 'New applicant profile created'),
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
INSERT INTO systemLogs (logId, userId, action, actionTaken)
VALUES
    (1,2, 'login', 'other'),
    (2,3, 'login', 'other'),
    (3,4, 'login', 'other'),
    (4,5, 'login', 'other'),
    (5,6, 'login', 'other'),
    (6,7, 'login', 'other'),
    (7,8, 'login', 'other'),
    (8,9, 'login', 'other'),
    (9,10, 'login', 'other'),
    (10,2, 'logout', 'other'),
    (11,3, 'logout', 'other'),
    (12,4, 'logout', 'other'),
    (13,5, 'logout', 'other'),
    (14,6, 'logout', 'other'),
    (15,7, 'logout', 'other'),
    (16,8, 'logout', 'other'),
    (17,9, 'logout', 'other'),
    (18,10, 'logout', 'other');




DESCRIBE users;
