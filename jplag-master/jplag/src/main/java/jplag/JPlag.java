package jplag;


import jplag.options.CommandLineOptions;
import jplag.qiubochenutil.MydatabaseMethod;

public class JPlag {

	public static void main(String[] args) {
        String[] argstmp=null;
        MydatabaseMethod mydatabaseMethod =new MydatabaseMethod(args);//qiubochen change
        if(mydatabaseMethod.judgeArgs(args)) {
            argstmp = mydatabaseMethod.changeArgs(args);//qiubochen change
        }else {
            argstmp = args;
        }
		if (args.length == 0)
			CommandLineOptions.usage();
		else {
            try {

                CommandLineOptions options = new CommandLineOptions(argstmp, null);
                Program program = new Program(options);

                System.out.println("initialize ok");
                program.run();
            }
            catch(ExitException ex) {
                System.out.println("Error: "+ex.getReport());
		System.exit(1);
            }
		}
	}
}
