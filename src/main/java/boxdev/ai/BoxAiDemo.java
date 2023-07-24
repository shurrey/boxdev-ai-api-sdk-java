package boxdev.ai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import java.net.URL;


import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxAPIRequest;
import com.box.sdk.BoxAPIResponse;
import com.box.sdk.BoxConfig;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import static com.box.sdk.StandardCharsets.UTF_8;

import com.box.sdk.http.HttpMethod;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option.Builder;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDateTime;

import boxdev.ai.objects.BoxAiDialogHistory;
import boxdev.ai.objects.BoxAiItem;
import boxdev.ai.objects.BoxAiRequest;
import boxdev.ai.objects.BoxAiRequestConfig;
import boxdev.ai.objects.BoxAiResponse;
import boxdev.ai.services.BoxAiService;

public class BoxAiDemo {

	private static final Logger log = LoggerFactory.getLogger(BoxAiDemo.class);

	public static void main(String[] args) {

		log.info("args: " + args.toString());
		CommandLine commandLine;
        String prompt = "";
        String fileId = "";
        String token = "";
        String answer = "";
        JSONArray answerJson = null;
        
		Option prompt_parameter = Option.builder("p")
            .required(true)
            .hasArgs()
            .desc("The question you'd like to ask Box AI")
            .longOpt("prompt")
            .build();
        Option fileId_parameter = Option.builder("f")
            .required(true)
            .hasArgs()
            .desc("The Box file id you'd like to ask Box AI about")
            .longOpt("file")
            .build();
        Option token_parameter = Option.builder("t")
            .required(true)
            .hasArgs()
            .desc("Your developer token")
            .longOpt("token")
            .build();
		Option property  = Option.builder("D")
			.hasArgs()
			.valueSeparator('=')
			.build();
        
		Options options = new Options();
        CommandLineParser parser = new DefaultParser();

        options.addOption(prompt_parameter);
        options.addOption(fileId_parameter);
        options.addOption(token_parameter);
		options.addOption(property);
        

        try
        {
            commandLine = parser.parse(options, args);

            if (commandLine.hasOption("p"))
            {
                log.info("Prompt is present.  The value is: ");
                log.info(commandLine.getOptionValue("p"));
                prompt = commandLine.getOptionValue("p");
            }

            if (commandLine.hasOption("f"))
            {
                log.info("file is present.  The value is: ");
                log.info(commandLine.getOptionValue("f"));
                fileId = commandLine.getOptionValue("f");
            }

            if (commandLine.hasOption("t"))
            {
                log.info("token is present.  The value is: ");
                log.info(commandLine.getOptionValue("t"));
                token = commandLine.getOptionValue("t");
            }

            
			String[] remainder = commandLine.getArgs();
			log.info("Remaining arguments: ");
			for (String argument : remainder)
			{
				log.info(argument);
				log.info(" ");
			}

			log.info(" ");

        }
        catch (ParseException exception)
        {
            System.out.print("Parse error: ");
            System.out.println(exception.getMessage());
        }

        String body = "{"
        .concat( "\"mode\": \"single_item_qa\"," )
        .concat( "\"prompt\":" )
        .concat( "\"" + prompt + "\"")
        .concat( ",")
        .concat( "\"items\": [")
        .concat( "{")
        .concat( "\"id\": " )
        .concat( "\"" + fileId + "\",")
        .concat( "\"type\": \"file\"" )
        .concat(    "}")
        .concat( "],")
        .concat( "\"dialogue_history\": [ ],")
        .concat( "\"config\": {")
        .concat(    "\"is_streamed\": true")
        .concat( "}")
        .concat( "}");
        log.info(body);
        
        try {
            BoxAPIConnection api = new BoxAPIConnection(token);
            URL boxAiUrl = new URL("https://api.box.com/2.0/ai/ask");
            BoxAPIRequest req = new BoxAPIRequest(api, boxAiUrl, HttpMethod.POST);
            req.setBody(body);
            BoxAPIResponse res = req.send();

            InputStream answerStream = res.getBody();

            try {
                InputStreamReader reader = new InputStreamReader(answerStream, UTF_8);
                StringBuilder builder = new StringBuilder();
                char[] buffer = new char[1024];

                int read = reader.read(buffer, 0, 1024);
                while (read != -1) {
                    builder.append(buffer, 0, read);
                    read = reader.read(buffer, 0, 1024);
                    
                    System.out.print(builder.toString());
                }

                reader.close();
                answer = builder.toString();

                answerJson = new JSONArray(answer);

                //answer = res.bodyToString();

            
                /*ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                for (int length; (length = answerStream.read(buffer)) != -1; ) {
                    result.write(buffer, 0, length);
                }

                answer = result.toString("UTF-8");

                //answerJson = new JSONArray("[" + answer + "]");

                BufferedReader bufferedReader = new BufferedReader(new StringReader(answer));

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }

                answerJson = new JSONObject(sb.toString()); 
                
                
                //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(answerStream));
                //*/
                
            } catch(Exception e) {
                // if any I/O error occurs
                e.printStackTrace();
            } //finally {
                // releases system resources associated with this stream
            //    if(answerStream!=null)
            //    answerStream.close();
            //}

        } catch (Exception e) {
            log.info("Error processing: " + e.toString());
        }
    }
}