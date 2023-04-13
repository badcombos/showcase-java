FROM httpd:2.4


RUN apt update

COPY httpd.conf /usr/local/apache2/conf/httpd.conf
COPY cgi-bin/ /usr/local/apache2/cgi-bin/

RUN chmod -R 755 /usr/local/apache2/cgi-bin/
RUN apt install -y libcam-pdf-perl
RUN apt install -y libdbd-mysql-perl
RUN perl -MCPAN -e 'install Bundle::DBI'

#CMD httpd-foreground -c "LoadModule cgid_module modules/mod_cgid.so"

##RUN a2enmod cgi