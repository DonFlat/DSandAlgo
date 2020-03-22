# DSandAlgo
## Taken notes

* Difference between int and Integer (primitive type and its wrapper class): [Stackoverflow answer](https://stackoverflow.com/questions/8660691/what-is-the-difference-between-integer-and-int-in-java)
	* int stores the actual binary value for the integer. Integer object holds a reference, just like other object type
	* Integer.parseInt() is a static method from Integer class
	* Integer is class with single field of type int
	* When need integer needs to be treated like class, such as generic or nullable *though IDK what are they*, use Integer
