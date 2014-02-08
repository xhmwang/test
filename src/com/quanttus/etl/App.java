package com.quanttus.etl;

import java.util.Properties;

import cascading.flow.FlowConnector;
import cascading.flow.FlowDef;
import cascading.flow.hadoop.HadoopFlowConnector;
//import cascading.flow.local.LocalFlowConnector;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.property.AppProps;
import cascading.scheme.hadoop.TextLine;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.hadoop.Hfs;
import cascading.tap.hadoop.Lfs;
import cascading.tap.local.FileTap;
//import cascading.tap.local.FileTap;
import cascading.tuple.Fields;

public class App {

	public static void main(String[] args) {
		String accessKey = "AKIAINNBUGENMYZH53SA"; //args[2];
        String secretKey = "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"; 
        //String inpath = "/tmp/qttSample";
        String bucket = "s3n://" + accessKey + ":"+ secretKey + "@s3.ingest.use-case1/";
		String inpath = bucket + "new/";				
        String outpath = bucket + "processed/";
        
		Properties props = new Properties();
	    AppProps.setApplicationJarClass( props, App.class );
        FlowConnector flowConnector = new HadoopFlowConnector(props);
        
        Tap inTap = new Hfs( new TextLine(), inpath, SinkMode.KEEP);
 
        Fields fields = new Fields("line", String.class);
        Pipe docPipe = new Each( "vitals", fields, new VitalsFlattenerUdf(fields), Fields.RESULTS );

        Tap outTap = new Hfs( new TextLine(), outpath, SinkMode.REPLACE);
 
        FlowDef flowDef = FlowDef.flowDef().addSource( docPipe, inTap ).addTailSink( docPipe, outTap );
        
        flowConnector.connect( flowDef ).complete();
	}

}
