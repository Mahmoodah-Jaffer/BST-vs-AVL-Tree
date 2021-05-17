#!/bin/bash
#Create set of N data from CSV file to compare the best, average and worst cases

set -x

for N in {1..500}
do
	head -n $N test.txt > subSet2.txt
	java PowerAVLAppTest subSet2.txt >> SpeedAVL.csv

	echo "" >> SpeedAVL.csv

done
rm -f subSet2.csv 