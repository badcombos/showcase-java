#!/usr/bin/perl -wT

use strict;
use warnings;
use DBI;

print "Content-type: text/html\n\n";
print "<html><head><title>Random Word Generator</title></head>\n";
print "<body>\n";
print "<h2> 10 Random Words are:</h2>\n";


my $dbh = DBI->connect("DBI:mysql:website;host=database;port=3306",'root','root');

if(!$dbh){
	print("<h1>ERROR: failed to connect to MySQL database</h1>");
        die "failed to connect to MySQL database DBI->errstr()";
}else{
        print("<h2> Connected to MySQL server successfully.</h2>\n");
}

#my %lettersHash = ('a' => 8.167, 'b' => 1.492, 'c' => 2.782, 'd' => 4.253, 'e' => 12.702,'f' => 2.228,'g' => 2.015,'h' => 6.094,'i' => 6.966, 'j' => 0.153, 'k' => 0.772,'l' => 4.025,'m' => 2.406, 'n' => 6.749, 'o' => 7.507, 'p' => 1.929, 'q' => 0.095, 'r' => 5.987, 's' => 6.327, 't' => 9.056, 'u' => 2.758, 'v' => 0.978, 'w' => 2.360, 'x' => 0.150, 'y' => 1.974, 'z' => 0.074 );
my %lettersHash = ('a' => 0, 'b' => 0, 'c' => 0, 'd' => 0, 'e' => 0,'f' => 0,'g' => 0,'h' => 0,'i' => 0, 'j' => 0, 'k' => 0,'l' => 0,'m' => 0, 'n' => 0, 'o' => 0, 'p' => 0, 'q' => 0, 'r' => 0, 's' => 0, 't' => 0, 'u' => 0, 'v' => 0, 'w' => 0, 'x' => 0, 'y' => 0, 'z' => 0 );


# prepare SQL statement
my $sth = $dbh->prepare("SELECT letter, weight FROM weights")
                   or die "prepare statement failed: $dbh->errstr()";

$sth->execute() or die "execution failed: $dbh->errstr()"; 

my($db_letter,$db_weight);

# loop through each row of the result set, and print it
while(($db_letter,$db_weight) = $sth->fetchrow()){
   #print("<h3>$db_letter, $db_weight </h3>\n");
   $lettersHash{$db_letter} = $db_weight;                   
}
$sth->finish();
$dbh->disconnect();


my %vowelsHash = %lettersHash{'a', 'e', 'i', 'o', 'u', 'y'};
my %consHash = %lettersHash{'b', 'c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','z'};
my $vowelsSum = sumH(%vowelsHash);
my $consSum = sumH(%consHash);
for (1..10){
    my $string=randChar();
    for(my $i=0,my $x=isVowel($string)>-1?1:0; $i<randRange(3..15);$i++,$x^=1){
        $string .= $x==0?randVowel():randCons();
    }
    print "<h3>", $string, "</h3>\n";
}
sub randChar{
    return weightedIndex(100,%lettersHash);
}
sub randVowel{
    return weightedIndex($vowelsSum,%vowelsHash)
}
sub randCons{
    return weightedIndex($consSum,%consHash);
}
sub randRange{
    my @a = @_;
    return $a[rand(@a)];
}
sub isVowel{
    return index("aeiouy",$_[0]);
}
sub weightedIndex{
    my ($weightedSum, %h) = @_;
    my $sum=0, $weightedSum*=rand(); 
    foreach my $key ( keys %h){
        $sum += $h{$key}; 
        return $key if $sum > $weightedSum;
    }
}
sub sumH{
    my (%h) = @_;
    my $sum =0;
    foreach my $key ( keys %h){
        $sum += $h{$key};
    }
    return $sum;
}


print "</body></html>\n";
