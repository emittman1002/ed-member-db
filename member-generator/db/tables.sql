drop database if exists raw_data;
create database raw_data;

use raw_data;

drop table if exists MemberGroup;
create table MemberGroup(
	id integer primary key auto_increment,
    groupName varchar(128) not null,
    groupNumber varchar(128) not null
);

drop table if exists GroupMember;
create table GroupMember(
	id integer primary key auto_increment,
    lastName varchar(128) not null,
    middleName varchar(128) not null,
    firstName varchar(64) not null,
    ssn varchar(11),
    dateOfBirth date
);
