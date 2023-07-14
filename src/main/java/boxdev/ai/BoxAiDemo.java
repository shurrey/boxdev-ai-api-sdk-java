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

        BoxAiRequest request = new BoxAiRequest();

        BoxAiDialogHistory history = new BoxAiDialogHistory();
        BoxAiItem item = new BoxAiItem();
        BoxAiItem[] items = new BoxAiItem[1];
        BoxAiRequestConfig config = new BoxAiRequestConfig();

        item.setType("file");
        item.setId(fileId);

        items[0] = item;

        config.setIsStreamed(false);

        request.setMode("single_item_qa");
        request.setPrompt(prompt);
        request.setItems(items);
        request.setConfig(config);

        BoxAiService service = new BoxAiService(token);

        BoxAiResponse response = service.send(request);

        log.info("BoxAIResponse string is " + response.getAnswer());

	}

}