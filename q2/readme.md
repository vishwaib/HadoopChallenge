Case:
Find out Salary trends between Male and Female employees during each decade in every department. The output should be in the following format: Department, Decade, Gender, avg_salary. Please upload the final CSV along with the code and jar files(if any) to your github and provide the link below.

SQOOP
GET all data from MySql and Store it in HDFS

PIG
1.title = load '/user/vishwa/sqoop/titles/part-m-00000' Using PigStorage(',') as (empno:int, title:chararray, from_date:Datetime, to_date:Datetime);
2.employees = load '/user/vishwa/sqoop/employees/part-m-00000' Using PigStorage(',') as (emp_no:int, birth_date:Datetime, first_name:chararray, last_name:chararray, gender:chararray, hire_date:Datetime);
3.salaries = load '/user/vishwa/sqoop/salaries/part-m-00000' Using PigStorage(',') as (emp_no:int, salary:int, from_date:Datetime, to_date:Datetime);
4.dept_employee = load '/user/vishwa/sqoop/dept_emp' Using PigStorage(',') as (emp_no:int, dept_no:chararray, from_date:Datetime, to_date:Datetime);
5.title_emp_sal_deptemp_join = Join title by empno, employees by emp_no, salaries by emp_no, dept_employee by emp_no; 
6.title_emp_sal_deptemp_join_fields = Foreach title_emp_sal_deptemp_join Generate employees::emp_no,dept_employee::dept_no, employees::gender, salaries::salary, salaries::to_date;
7.dec_80_90 = Filter title_emp_sal_deptemp_join_fields By to_date > ToDate('1980-01-01', 'yyyy-MM-dd') and to_date < ToDate('1990-01-01', 'yyyy-MM-dd');
8.dec_90_2k = Filter title_emp_sal_deptemp_join_fields By to_date > ToDate('1990-01-01', 'yyyy-MM-dd') and to_date < ToDate('2000-01-01', 'yyyy-MM-dd');
9.dec_2k = Filter title_emp_sal_deptemp_join_fields By to_date > ToDate('2000-01-01', 'yyyy-MM-dd');
10.STORE dec_80_90 INTO '/HadoopChallenge/q2/decade_80_90/pig_op' using PigStorage(',');
11.STORE dec_90_2k INTO '/HadoopChallenge/q2/decade_90_2k/pig_op' using PigStorage(',');
12.STORE dec_2k INTO '/HadoopChallenge/q2/decade_2k/pig_op' using PigStorage(',');

Run Hadoop MR Program to sort the Date on Department Level, and then separate it out on Gender

Decade 1980 - 1990
hadoop jar AvgDepartmentSal_dec8090.jar q2.AvgDepartmentSal /HadoopChallenge/q2/decade_80_90/pig_op/part-r-00000 /HadoopChallenge/q2/decade_80_90/mr_op
Decade 1990 - 2000
hadoop jar AvgDepartmentSal_dec902k.jar q2.AvgDepartmentSal /HadoopChallenge/q2/decade_90_2k/pig_op/part-r-00000 /HadoopChallenge/q2/decade_90_2k/mr_op
Decade 2000 - 2010
hadoop jar AvgDepartmentSal_dec2k2k10.jar q2.AvgDepartmentSal /HadoopChallenge/q2/decade_2k/pig_op/part-r-00000 /HadoopChallenge/q2/decade_2k/mr_op

OP as CSV
mr_op = load '/HadoopChallenge/q2/decade_2k/mr_op/part-r-00000' as (key:chararray, value:chararray);
store mr_op INTO '/HadoopChallenge/q2/decade_2k/finalop_csv' using PigStorage(',');

mr_op = load '/HadoopChallenge/q2/decade_80_90/mr_op/part-r-00000' as (key:chararray, value:chararray);
store mr_op INTO '/HadoopChallenge/q2/decade_80_90/finalop_csv' using PigStorage(',');

mr_op = load '/HadoopChallenge/q2/decade_90_2k/mr_op/part-r-00000' as (key:chararray, value:chararray);
store mr_op INTO '/HadoopChallenge/q2/decade_90_2k/finalop_csv' using PigStorage(',');


Copy File from HDFS to LOCAL and rename it as ".csv" file
