Case:
Find out CTC of each department grouped by each dept_manager's name and number of employees under the manager. The output should be in the following format: Dept_no, Department, Manager, CTC, total_emps. Please upload the final CSV along with the code and jar files(if any) to your github and provide the link below.

SQOOP
GET all data from MySql and Store it in HDFS

PIG
1.employees = load '/user/vishwa/sqoop/employees/part-m-00000' Using PigStorage(',') as (emp_no:int, birth_date:Datetime, first_name:chararray, last_name:chararray, gender:chararray, hire_date:Datetime);
2.salaries = load '/user/vishwa/sqoop/salaries/part-m-00000' Using PigStorage(',') as (emp_no:int, salary:int, from_date:Datetime, to_date:Datetime);
3.dept_employee = load '/user/vishwa/sqoop/dept_emp/part-m-00000' Using PigStorage(',') as (emp_no:int, dept_no:chararray, from_date:Datetime, to_date:Datetime);
4.dept_manager = load '/user/vishwa/sqoop/dept_manager/part-m-00000' Using PigStorage(',') as (dept_no: chararray,emp_no:int,from_date:Datetime, to_date:Datetime);
5.emp_sal_demp_demgr_join_1 = Join dept_manager By dept_no, dept_employee By dept_no;
6.emp_sal_demp_demgr_join_2 = Join emp_sal_demp_demgr_join_1 By dept_manager::emp_no, employees by emp_no;
7.emp_sal_demp_demgr_fields = FOREACH emp_sal_demp_demgr_join_2 GENERATE dept_manager::dept_no as manager_dept, dept_manager::emp_no as manager_empno, 8.employees::first_name as manager_fname,employees::last_name as manager_lname, dept_employee::emp_no as employee_empno,dept_employee::dept_no as employee_dept, dept_manager::from_date as manager_from, dept_manager::to_date as manager_to, dept_employee::from_date as employee_from, dept_employee::to_date as employee_to;
9.emp_sal_demp_demgr_fields_filter = FILTER emp_sal_demp_demgr_fields BY ((employee_from >= manager_from and employee_from <= manager_to) or (employee_to >= manager_from and employee_from <= manager_to));
10.Store emp_sal_demp_demgr_fields_filter INTO '/HadoopChallenge/q4/pig_op' Using PigStorage(',');

Run Hadoop MR Program to get employees growth
11.hadoop jar DeptManagerDetails.jar q4.DeptManagerDetails /HadoopChallenge/q4/pig_op/part-r-00000 /HadoopChallenge/q3/mr_op/
12.mr_op = load '/HadoopChallenge/q4/mr_op/part-r-00000' as (key:chararray, value:chararray);
13.store mr_op INTO '/HadoopChallenge/q4/finalop_csv' using PigStorage(',');

Copy File from HDFS to LOCAL and rename it as ".csv" file
