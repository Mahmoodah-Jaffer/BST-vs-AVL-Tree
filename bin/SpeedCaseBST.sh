#!/bin/bash
#Create set of N data from CSV file to compare the best, average and worst cases

set -x

for N in {1..500}
do
	head -n $N test.txt > subSet3.txt
	java PowerBSTAppTest subSet3.txt >> SpeedBST.csv

	echo "" >> SpeedBST.csv

done
rm -f subSet3.txt 