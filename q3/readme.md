Case:
Find out each employee who have had a lateral or vertical movement in their career and provide their EmpID, Name, Old_designation, Old_dept, New_designation, New_dept. Please upload the final CSV along with the code and jar files(if any) to your github and provide the link below.

SQOOP
GET all data from MySql and Store it in HDFS

PIG
1.title = load '/user/vishwa/sqoop/titles/part-m-00000' Using PigStorage(',') as (empno:int, title:chararray, from_date:Datetime, to_date:Datetime);
2.dept_employee = load '/user/vishwa/sqoop/dept_emp' Using PigStorage(',') as (emp_no:int, dept_no:chararray, from_date:Datetime, to_date:Datetime);
3.employees = load '/user/vishwa/sqoop/employees/part-m-00000' Using PigStorage(',') as (emp_no:int, birth_date:Datetime, first_name:chararray, last_name:chararray, gender:chararray, hire_date:Datetime);
4.title_dept_emp_empl_join = JOIN title by empno, dept_employee by emp_no, employees by emp_no;
5.title_dept_emp_empl_join_fields = FOREACH title_dept_emp_empl_join GENERATE employees::emp_no, employees::first_name, employees::last_name, title::title, dept_employee::dept_no, dept_employee::from_date as dept_from, dept_employee::to_date as dept_to, title::from_date as title_from, title::to_date as title_to;
6.title_dept_emp_empl_join_fields_filter = FILTER title_dept_emp_empl_join_fields BY ((title_from >= dept_from and title_from <= dept_to) or (title_to >= dept_from and title_to <= dept_to));
7.title_dept_emp_empl_join_filter_ordered = ORDER title_dept_emp_empl_join_fields_filter By dept_from ASC;
8.Store title_dept_emp_empl_join_filter_ordered INTO '/HadoopChallenge/q3/pig_op' Using PigStorage(',');

Run Hadoop MR Program to get employees growth
9.hadoop jar EmpGrowth.jar q3.EmpGrowth /HadoopChallenge/q3/pig_op/part-r-00000 /HadoopChallenge/q3/mr_op/
10.mr_op = load '/HadoopChallenge/q3/mr_op/part-r-00000' as (key:chararray, value:chararray);
11.store mr_op INTO '/HadoopChallenge/q3/finalop_csv' using PigStorage(',');

Copy File from HDFS to LOCAL and rename it as ".csv" file
