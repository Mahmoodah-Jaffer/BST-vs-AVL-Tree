#!/bin/bash
#Create set of N data from CSV file to compare the best, average and worst cases

set -x

for N in {2..501}
do
	head -n $N cleaned_data.csv | tail -n $((N-1)) > subSet.csv

	while IFS=, read -ra arr;do
		${arr[0]} >> SpeedArray.txt
				echo -n "," >> SpeedArray.txt

	done < subSet.csv
	echo "" >> SpeedArray.txt
done
rm -f subSet.csv 