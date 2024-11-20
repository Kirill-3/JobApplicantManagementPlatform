-- Populate the User table
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

-- Populate the Applicants table
insert into applicants (firstName, lastName, email, phoneNumber, eventAttended, skill)
values
    ('Sarah', 'Connor', 'sconnor@example.com', '1234567890', 'Tech Expo', 'Java, Python'),
    ('John', 'Doe', 'jdoe@example.com', '2345678901', 'Career Fair', 'HTML, CSS, JavaScript'),
    ('Jane', 'Smith', 'jsmith@example.com', '3456789012', 'Hackathon', 'Python, Machine Learning'),
    ('Mike', 'Ross', 'mross@example.com', '4567890123', 'Job Fair', 'Project Management, Agile'),
    ('Emily', 'Blunt', 'eblunt@example.com', '5678901234', 'Tech Conference', 'DevOps, Cloud Computing'),
    ('Chris', 'Evans', 'cevans@example.com', '6789012345', 'Recruitment Drive', 'JavaScript, React'),
    ('Anna', 'Taylor', 'ataylor@example.com', '7890123456', 'Tech Expo', 'C++, Embedded Systems'),
    ('Tom', 'Hanks', 'thanks@example.com', '8901234567', 'Career Fair', 'Python, AI'),
    ('Sophia', 'Brown', 'sbrown@example.com', '9012345678', 'Hackathon', 'Data Analysis, SQL'),
    ('Max', 'Steel', 'msteel@example.com', '1234567899', 'Job Fair', 'Cybersecurity, Networking'),
    ('Eva', 'Longoria', 'elongoria@example.com', '2345678902', 'Career Fair', 'Ruby, Rails'),
    ('Jake', 'Gyllenhaal', 'jgyllenhaal@example.com', '3456789013', 'Tech Expo', 'Java, Spring'),
    ('Liam', 'Hemsworth', 'lhemsworth@example.com', '4567890124', 'Tech Conference', 'AWS, Docker'),
    ('Emma', 'Stone', 'estone@example.com', '5678901235', 'Recruitment Drive', 'JavaScript, Angular'),
    ('Olivia', 'Wilde', 'owilde@example.com', '6789012346', 'Hackathon', 'Kotlin, Android'),
    ('Noah', 'Centineo', 'ncentineo@example.com', '7890123457', 'Tech Expo', 'Flutter, Dart'),
    ('Lily', 'Collins', 'lcollins@example.com', '8901234568', 'Job Fair', 'PHP, Laravel'),
    ('Ethan', 'Hawke', 'ehawke@example.com', '9012345679', 'Career Fair', 'Rust, Systems Programming'),
    ('Ava', 'DuVernay', 'aduvernay@example.com', '1234567898', 'Hackathon', 'Go, Blockchain'),
    ('Jack', 'Black', 'jblack@example.com', '2345678903', 'Recruitment Drive', 'Scala, Functional Programming');

-- Populate the Applicant Preferences table
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
    (10, 'yes', 'no', 'no');

-- Populate the Application Details table
insert into applicationdetails(applicationId, currentPosition, status, CvPath, CoverLetterPath)
values
    (1, 'Software Engineer', 'External', NULL, NULL),
    (2, 'Frontend Developer', 'External', NULL, NULL),
    (3, 'Data Scientist', 'Internal', NULL, NULL),
    (4, 'Project Manager', 'External', NULL, NULL),
    (5, 'Cloud Engineer', 'Internal', NULL, NULL),
    (6, 'UI Developer', 'External', NULL, NULL),
    (7, 'Embedded Systems Engineer', 'External', NULL, NULL),
    (8, 'AI Specialist', 'External', NULL, NULL),
    (9, 'SQL Analyst', 'Internal', NULL, NULL),
    (10, 'Security Specialist', 'External', NULL, NULL);
