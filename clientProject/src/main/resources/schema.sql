create schema if not exists nhs_recruitment;

use nhs_recruitment;

drop table if exists applicantpreferences;
drop table if exists applicationdetails;
drop table if exists communicationsLogs;
drop table if exists systemLogs;
drop table if exists recruitmentLogs;
drop table if exists users;
drop table if exists applicants;

-- User Table
create table if not exists users(
    ID          int auto_increment primary key,
    username        varchar(50) not null unique,
    passwordHashed   CHAR(128) not null ,
    firstName       varchar(128) not null,
    lastName        varchar(128) not null,
    role            enum('admin', 'recruiter') not null,
    lastLogin       timestamp,
    createdAt        timestamp default current_timestamp
    ) engine = InnoDB;

-- Applicant Table
create table if not exists applicants(
    Id             int auto_increment primary key,
    firstName       varchar(128) not null ,
    lastName        varchar(128) not null ,
    location        varchar(100) not null ,
    email           varchar(100) not null unique,
    phoneNumber     varchar(15),
    eventAttended   varchar(100) not null,
    skill           text,
    createdAt        timestamp default current_timestamp,
    updatedAt        timestamp default current_timestamp on update current_timestamp
) engine = InnoDB;

-- Application Details Table
create table if not exists applicationDetails(
    Id           int auto_increment primary key,
    applicationId int,
    currentPosition varchar(100),
    status          enum('External','Internal') default 'External',
    CvPath          longblob,-- Store CV PDF as binary data
    CoverLetterPath longblob,-- Store Cover Letter PDF as binary data
    createdAt        timestamp default current_timestamp,
    updatedAt        timestamp default current_timestamp on update current_timestamp,
    foreign key (applicationId) references applicants(Id) on delete cascade
) engine = InnoDB;

--  Applicant Preferences Table
create table if not exists applicantPreferences
(
    Id          int auto_increment primary key,
    applicationId int not null,
    SubscribeToNewsLetter enum('Yes', 'No') default 'No',
    SubscribeToBulletins enum('Yes', 'No') default 'No',
    SubscribeToJobUpdates enum('Yes', 'No') default 'No',
    foreign key (applicationId) references applicants(Id) on delete cascade
) engine = InnoDB;


-- System Logs Table
create table if not exists systemLogs(
    Id              int auto_increment primary key,
    userId          int not null,
    action          varchar(225),
    timestamp   datetime default current_timestamp,
    foreign key (userId) references users(ID) on delete cascade
) engine = InnoDB;


-- Communication Log Table
create table if not exists communicationsLogs(
    Id              int auto_increment primary key,
    applicationId   int not null,
    userId          int not null,
    type            enum('Email', 'SMS'),
    content         varchar(225),
    sentAt          datetime default current_timestamp,
    foreign key (applicationId) references applicants(Id) on delete cascade,
    foreign key (userId) references users(ID) on delete cascade
) engine = InnoDB;


-- Recruitment Logs Table
create table if not exists recruitmentLogs(
        Id              int auto_increment primary key,
        applicationId   int not null,
        userId          int not null,
        action          enum('Contacted', 'Interview Scheduled', 'Moved to internal'),
        notes           varchar(225),
        createdAt       timestamp default current_timestamp,
        foreign key (applicationId) references applicants(Id) on delete cascade,
        foreign key (userId) references users(ID) on delete cascade
) engine = InnoDB;
