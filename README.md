# hibernate-basics
* Introduction to ORM-Hibernate,
* Configuring session factory,
  1. Setup session factory using StandardServiceRegistry
  2. execute below statement
  
     mysql> SHOW PROCESSLIST;

	+-----+------+-----------------+--------------+---------+------+-------+------------------+

	| Id  | User | Host            | db           | Command | Time | State | Info             |

	+-----+------+-----------------+--------------+---------+------+-------+------------------+

	| 141 | root | localhost       | NULL         | Query   |    0 | NULL  | SHOW PROCESSLIST |

	| 165 | root | localhost:53662 | hibernate_db | Sleep   |    3 |       | NULL             |

	+-----+------+-----------------+--------------+---------+------+-------+------------------+

 
* Data modeling using basic type, automate schema generation, statement logging and stat
  * Automate schema generation : hbm2ddl.auto property enables automatic generation of database schemas directly into the database:-
 
	
	@validate : hibernate only validates whether the table and columns are exist or not. 
	
		     If the table doesn’t exist then hibernate throws an exception. 
	
	   	     Validate is the default value for hbm2ddl.auto.
	       				 
	
	@update : hibernate checks for the table and columns. 
	
	          If table doesn’t exist then it creates a new table 
	
	          and if a column doesn’t exist it creates new column for it.
	       			  
	
	@create-drop : hibernate first checks for a table and do the necessary operations 
	
		        and finally drops the table after all the operations are completed.
		        
	
	@create : hibernate first drops the existing table, then creates a new table 
	
	    	   and then executes operation on newly created table.

  * to generate a table from pojo an identifier must be specified
  * (no-argument) constructor for entity class must be provide

* Identifier and generator, unique constraints, indexes
* Enum, date, Attribute convertor
* Data modelin using collection,              
* Data modeling using entity, 
* Entity associationship                                  
