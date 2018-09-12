#!/usr/bin/bash
# make tophat fusion input file

inFile1="/home/chikako/tophat_output_cardio/test.sam"
inFile2="/home/chikako/tophat_output_cardio/unmapped.sam"
outFile1="/home/chikako/tophat_output_cardio/tmp1.sam"
outFile2="/home/chikako/tophat_output_cardio/tmp2.sam"
outFile3="/home/chikako/tophat_output_cardio/test2.sam"

# read a line from inFile1 to outFile1

while read -r line
do
set $line
if test $2 -eq 73 -o $2 -eq 89 -o $2 -eq 137 -o $2 -eq 153
then 
echo "$line" >> $outFile1
fi
done < "$inFile1"

#echo "\n" >> $outFile1

# convine, sort and remove files
cat $outFile1 $inFile2 > $outFile2
sort -k2,2n $outFile2 > $outFile3
#rm $outFile1
#rm $outFile2


