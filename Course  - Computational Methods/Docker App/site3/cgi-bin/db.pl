#!/usr/bin/perl
use strict;
use warnings;
use DBI;

my $dbh = DBI->connect("DBI:mysql:website",'user1','password');

if(!$dbh){
	die "failed to connect to MySQL database DBI->errstr()";
}else{
	print("Connected to MySQL server successfully.\n");
}


#die "failed to connect to MySQL database:DBI->errstr()" unless($dbh);
#print("connected to database");
