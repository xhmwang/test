package com.quanttus.etl;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

public class VitalsFlattenerUdf extends BaseOperation implements Function{

	private static final long serialVersionUID = 4104527719460945875L;
	
	  public VitalsFlattenerUdf( Fields fieldDeclaration ) {
	    super(1, fieldDeclaration );
	    }
	  
	  public void operate( FlowProcess flowProcess, FunctionCall functionCall ) 
	    {
	    // get the arguments TupleEntry
	    TupleEntry arguments = functionCall.getArguments();

	    Tuple result = new Tuple();
	    
	    String in = arguments.getString("line"); //System.out.println(in);
	    String res = null;
	    try {
	    	res =  new VitalsFlattener().flatten(in);;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    result.add( res ); //System.out.println(result);

	    // return the result Tuple
	    functionCall.getOutputCollector().add( result ); 
	    }
	  
	  public static void main(String[] args) {
		  //anything to test?
	  }
}
