Case :
Calculate average the salary of each 'Senior' employee. Please upload the final CSV along with the code and jar files(if any) to your github and provide the link below. The CSV should be formatted as empid, Name, Designation, Avg_salary

SQOOP
GET all data from MySql and Store it in HDFS

PIG
1. title = load '/user/vishwa/sqoop/titles/part-m-00000' Using PigStorage(',') as (empno:int, title:chararray, from_date:Datetime, to_date:Datetime);
2. employees = load '/user/vishwa/sqoop/employees/part-m-00000' Using PigStorage(',') as (emp_no:int, birth_date:Datetime, first_name:chararray, last_name:chararray, 	  gender:chararray, hire_date:Datetime);
3. salaries = load '/user/vishwa/sqoop/salaries/part-m-00000' Using PigStorage(',') as (emp_no:int, salary:int, from_date:Datetime, to_date:Datetime);
4. title_emp_sal_join = Join title by empno, employees by emp_no, salaries by emp_no;
5. title_emp_sal_join_fields = FOREACH title_emp_sal_join GENERATE title::empno, employees::first_name, employees::last_name, title::title, salaries::salary;
6. title_emp_sal_join_fields_filter = FILTER title_emp_sal_join_fields By title::title == 'Senior Engineer' or title::title == 'Senior Staff';
7. title_emp_sal_join_fields_filter_order = ORDER title_emp_sal_join_fields_filter BY empno ASC;
8. STORE title_emp_sal_join_fields_filter_order INTO '/HadoopChallenge/q1/pigop_firststep' using PigStorage(',');

Run Hadoop MR Prgram to Calculate Avarage Salary
9. hadoop jar AvgSalary.jar q1.AvgSalary /HadoopChallenge/q1/pigop_firsstep/part-r-00000 /HadoopChallenge/q1/mr_output/
10. mr_op = LOAD '/HadoopChallenge/q1/mr_output/part-r-00000' as (key:chararray, value:chararray);
11. store mr_op INTO '/HadoopChallenge/q1/finalop_csv' using PigStorage(',');

Copy File from HDFS to LOCAL and rename it as ".csv" file
