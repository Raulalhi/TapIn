CREATE TABLE COMPANY (
	CID NUMBER(3) PRIMARY KEY,
	CNAME VARCHAR2(30) NOT NULL,
	CLOCATION VARCHAR2(30) NOT NULL
);

CREATE TABLE EMPLOYER (
	YERID NUMBER(3) PRIMARY KEY,
	YERNAME VARCHAR2(30) NOT NULL,
	USERNAME VARCHAR2(30) NOT NULL,
	PASSWORD VARCHAR2(30) NOT NULL
	)

CREATE TABLE EMPLOYEE (
	YEEID NUMBER(3) PRIMARY KEY,
	YEENAME VARCHAR2(30) NOT NULL,
	YEESURNAME VARCHAR2(30) NOT NULL,
	JOB VARCHAR2(30) NOT NULL,
	WAGE NUMBER(4,2) NOT NULL,
	PARTIME BOOLEAN DEFAULT FALSE,
	--
	TID NUMBER(3),
	EMPLOYER NUMBER(3)
	--
	);

--CREATE TABLE TIMETABLE (
	--ID NUMBER(3) PRIMARY KEY,
	--MONDAY NUMBER(2),
	--TUESDAY NUMBER(2),
	--WEDNESDAY NUMBER(2),
	--THURSDAY NUMBER(2),
	--FRIDAY NUMBER(2),
	--SATURDAY NUMBER(2),
	--SUNDAY NUMBER(2),
	--CHECKEDIN BOOLEAN DEFAULT FALSE,
	--WEEKLYPAYMENT NUMBER(6,2)

	--);

CREATE TABLE TIMETABLE2 (
	ID NUMBER(3) PRIMARY KEY,
	DAYOFWEEK NUMBER(1),
	STARTTIME TIME,
	ENDTIME TIME,
	TASKDESC VARCHAR2(50),
	CHECKEDIN BOOLEAN DEFAULT FALSE

	);