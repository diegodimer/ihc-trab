CREATE SCHEMA "IHC";

CREATE TABLE "IHC".Advisor (
Id serial PRIMARY KEY,
Name TEXT,
Login TEXT,
Password TEXT,
Email TEXT,
Phone TEXT );

CREATE TABLE "IHC"."User" (
Id serial PRIMARY KEY,
Name TEXT,
Login TEXT,
Password TEXT,
Email TEXT,
Phone TEXT,
AdvisorId INTEGER,
FOREIGN KEY(AdvisorId) REFERENCES Advisor(Id));