#!/bin/bash
#Create set of N data from CSV file to compare the best, average and worst cases

set -x

for N in {2..501}
do
	head -n $N cleaned_data.csv | tail -n $((N-1)) > subSet1.csv
	java PowerBSTAppTest subSet1.csv >> SpeedBSTinsert.csv

	echo "" >> SpeedBSTinsert.csv

done
rm -f subSet1.csv