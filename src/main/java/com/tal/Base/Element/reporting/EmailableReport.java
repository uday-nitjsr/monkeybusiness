package com.tal.Base.Element.reporting;

import java.io.*;

/**
 * Created by asus on 5/26/2017.
 */
public class EmailableReport {
    protected static PrintWriter writer;
    protected PrintWriter summaryWriter;

    public void generateReport() throws IOException {
        writer = createWriter("D:/Output", "emailable-report2.html");
        writeDocumentStart();
        writeHead();
        writer.print("<body>");
        writeScenarioSummary();
        writer.print("<body>");
        writeDocumentEnd();
        writer.close();
    }

    protected PrintWriter createWriter(String outdir, String fileName) throws IOException {
        new File(outdir).mkdirs();
        return new PrintWriter(new BufferedWriter(new FileWriter(new File(
                outdir, fileName))));
    }

    protected void writeDocumentStart() {
        writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
        writer.print("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    }
    protected void writeDocumentEnd() {
        writer.print("</html>");
    }

    protected void writeHead() {

        writer.print("<head>");
        writer.print("<title>TestNG Report</title>");
        writeStylesheet();
        writeJavascript();
        writer.print("</head>");
    }

    protected void writeStylesheet() {

        writer.print("<style type=\"text/css\">");
        writer.print("table {margin-bottom:10px;border-collapse:collapse;empty-cells:show}");
        writer.print("th,td {border:1px solid #009;padding:.25em .5em}");
        writer.print("ul.method-list { clear: both; padding-bottom:20px;margin-bottom: 20px;padding-top:10px;border-bottom: 1px solid rgb(204,204,204);width:80%}");  // added
        writer.print(".scenario-name {font-weight:bold; position:relative}");
        writer.print(".scenario-name div.test-desc {display:none}");
        writer.print(".scenario-name:hover div.test-desc {display:block}");
        writer.print("div.test-desc {background-color:#F5F5F5; border: 2px solid #808080; font-weight: normal; left: 250px; overflow: auto; padding: 10px; position: absolute; top: 0px; width: 400px}");
        writer.print("span.testpass {color: #090}");
        writer.print("span.testfail {color: #900}");
        writer.print("span.testskip {color: #CC9900}");
        writer.print("span.test-name {width:80%}");
        writer.print("span.statuslbl {float:right;position:relative}");
        writer.print("span div.msg {display:none}");
        writer.print("span:hover div.msg {display:block}");
        writer.print("div.msg {background-color:#FFFFFF; ; font-weight: normal; right: 50px; overflow: auto; padding: 10px; position: absolute; top: 0px; width: 400px}");
        writer.print("div.error.msg {border: 1px solid #990000}");
        writer.print("div.log.msg {border: 1px solid #090}");
        writer.print("div.status-bar {background-color: #FFFFFF;border: 5px ridge #808080;float: right;padding-left: 10px;padding-top: 10px;position: fixed;right: 0;top: 0; width: 181px; z-index: 2}");
        writer.print(".video-link {float: right;margin-top: -24px;margin-right: -30px}");
        writer.print(".video-container {width: 80%}");
        writer.print(".video {display: block;padding: 30px}");
        writer.print(".player{height:450px;padding-left:10px}");
        // addded complete
        writer.print("th {vertical-align:bottom;text-align:left}");
        writer.print("td {vertical-align:top}");
        writer.print("table a {font-weight:bold}");
        writer.print(".stripe td {background-color: #E6EBF9}");
        writer.print(".num {text-align:right}");
        writer.print(".passedodd td {background-color: #3F3}");
        writer.print(".passedeven td {background-color: #0A0}");
        writer.print(".skippedodd td {background-color: #DDD}");
        writer.print(".skippedeven td {background-color: #CCC}");
        writer.print(".failedodd td,.attn {background-color: #F33}");
        writer.print(".failedeven td,.stripe .attn {background-color: #D00}");
        writer.print(".stacktrace {white-space:pre;font-family:monospace}");
        writer.print(".totop {font-size:85%;text-align:center;border-bottom:2px solid #000}");
        writer.print("</style>");
    }

    protected void writeJavascript() {
        writer.print("<script type=\"text/javascript\" src=\"https://saucelabs.com/flowplayer/example/flowplayer-3.2.13.min.js\" ></script>" +
                "<script type=\"text/javascript\"> function showHideVideo(videoName){var vc=document.getElementById(videoName+'cont');" +
                "var velm = document.getElementById(videoName);	var visibility =  velm.style.display; if(visibility ==\"none\"){ velm.style.display= 'block';" +
                "vc.innerHTML=\"Hide Video\";	}else{velm .style.display= 'none'; vc.innerHTML=\"Show Video\";	} }");

        String strJS= "var scripts = document.getElementsByTagName('script');" +
                "var script = scripts[scripts.length - 1];" +
                "var loadScript = function (src) {" +
                "  var s, r;" +
                "  r = false;" +
                "  s = document.createElement('script');" +
                "  s.type = 'text/javascript';" +
                "  s.src = src;" +
                "  s.onload = s.onreadystatechange = function() {" +
                "    if ( !r && (!this.readyState || this.readyState == 'complete') )" +
                "    {" +
                "      r = true;" +
                "    }" +
                "  };" +
                "  document.body.appendChild(s);" +
                "};" +
                "var loadVideo = function (playerId,playerDiv,jobId,authId) {" +
                "  var flowerrorbox = document.createElement(\"div\");" +
                "  flowerrorbox.id = \"flowerrorbox\";" +
                "" +
                "  var playerContainer = document.createElement(\"a\");" +
                "  playerContainer.style.height = \"450px\";" +
                "  playerContainer.style.display = \"block\";" +
                "  playerContainer.id = playerId;" +
                "" +
                "   var elements = document.getElementsByClassName(\"player\");" +
                "    for(var i=0;i<elements.length;i++){" +
                "        elements[i].parentNode.removeChild(elements[i]);" +
                "    }" +
                "	" +
                "   var elements = document.getElementsByClassName(\"video-link\");" +
                "   " +
                "   for(var i=0;i<elements.length;i++){" +
                "        elements[i].innerHTML=\"Show Video\";" +
                "    }	" +
                "	    " +
                "  var playersib =document.getElementById(playerDiv);" +
                "  playersib.innerHTML=\"<div id='player' class='player'></div>\";" +
                "  var player =document.getElementById(\"player\");" +
                "  player.innerHTML=playerContainer;" +
                "  flowplayer(\"player\", \"https://saucelabs.com/flowplayer/flowplayer-3.2.17.swf\", {" +
                "    clip:  {" +
                "        url: escape('https://saucelabs.com/jobs/'+jobId+'/video.flv?auth='+authId)," +
                "        provider: 'streamer'," +
                "        autoPlay: false," +
                "        autoBuffering: true" +
                "    }," +
                "    plugins: {" +
                "        streamer: {" +
                "            url: \"https://saucelabs.com/flowplayer/flowplayer.pseudostreaming-3.2.13.swf\"," +
                "        }," +
                "        controls: {" +
                "            mute: false," +
                "            volume: false," +
                "            backgroundColor: \"rgba(0, 0, 0, 0.7)\"" +
                "        }" +
                "    }," +
                "    onError: function(err) {" +
                "        if (err == 200) {" +
                "            $('#flowerrorbox').html(\"The video doesn't seem to be available. <a href=\\\"/docs/faq#the-video-seems-to-be-missing-even-though-the-test-finished\\\">Did the test finish long ago?</a>\");" +
                "        }" +
                "    }" +
                "  });" +
                "};" +
                "</script>";

        writer.print(strJS);
    }

    protected void writeScenarioSummary() {
        writer.print("<table id='actionDetails'>");
        writer.print("<thead>");
        writer.print("<tr>");
        writer.print("<th>Element Name</th>");
        writer.print("<th>Type</th>");
        writer.print("<th>Action</th>");
        writer.print("<th>Screenshot</th>");
        writer.print("</tr>");
        writer.print("</thead>");
    }

    public static void addRowToResult(String elementName,String Type,String action,String something){
        writer.print("<tr>");
        writer.print("<td>"+elementName+"/<td>");
        writer.print("<td>"+Type+"/<td>");
        writer.print("<td>"+action+"/<td>");
        writer.print("<td>"+something+"/<td>");
    }

    public static void main(String args[]) throws IOException {
        new EmailableReport().generateReport();
    }

}
