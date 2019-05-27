@author Indre Bogdan
	The objective of this project is to better understand lambda expressions and java streams.

The required task:

Description

	Consider the task of analyzing the behavior of a person recorded by a set of sensors.
The historical log of the person’s activity is stored as tuples (start_time, end_time, activity_label), where
start_time and end_time represent the date and time when each activity has started and ended while the
activity label represents the type of activity performed by the person: Leaving, Toileting, Showering,
Sleeping, Breakfast, Lunch, Dinner, Snack, Spare_Time/TV, Grooming.
The data is spread over several days as many entries in the log Activities.txt, taken from [1,2] and
downloadable from the file Activities.txt located in this folder.
Write a Java 1.8 program using lambda expressions and stream processing to do the tasks defined
below.

Tasks:

	1)Define a class MonitoredData with 3 fields: start time, end time and activity as string.
Read the data from the file Activity.txt using streams and split each line in 3 parts:
start_time, end_time and activity label and create a list of objects of type MonitoredData.


	2)Count how many days of monitored data appears in the log. 

	3)Count how many times has appeared each activity over the entire monitoring period.
Return a map of type <String, Int> representing the mapping of activities to their count.

	4)Count how many times has appeared each activity for each day over the monitoring period.
	
	5)For each line from the file map for the activity label the duration recorded on that line
(end_time-start_time)

	6)For each activity compute the entire duration over the monitoring period.

	7)Filter the activities that have 90% of the monitoring records with duration less than 5
minutes


REFERENCES
[1] Ordóñez, F.J.; de Toledo, P.; Sanchis, A. Activity Recognition Using Hybrid Generative/Discriminative
Models on Home Environments Using Binary Sensors. Sensors 2013, 13, 5460-5477.
[2] Available online at
https://archive.ics.uci.edu/ml/datasets/Activities+of+Daily+Living+(ADLs)+Recognition+Using+Binary+Se
nsors