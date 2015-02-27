import com.pi4j.wiringpi.Spi;


public class GoPiGo {

	public static byte WRITE_CMD = 0x40;
	public static byte READ_CMD = 0x41;
	
    // configuration
    byte IODIRA = 0x00; // I/O direction A
    byte IODIRB = 0x01; // I/O direction B
    byte IOCON  = 0x0A; // I/O config
    byte MOTOR  = 0x08;//0x04
    byte GPIOA  = 0x12; // port A
    byte GPIOB  = 0x13; // port B
    byte GPPUA  = 0x0C; // port A pullups
    byte GPPUB  = 0x0D; // port B pullups
    byte OUTPUT_PORT = GPIOA;
    byte INPUT_PORT  = GPIOB;
    byte INPUT_PULLUPS = GPPUB; 
	
	
	public GoPiGo() throws InterruptedException
	{
        // setup SPI for communication
        int fd = Spi.wiringPiSPISetup(0, 10000000);;
        if (fd <= -1) {
            System.out.println(" ==>> SPI SETUP FAILED");
            return;
        }
        
        // initialize
        //write(IOCON,  0x08);  // enable hardware addressing
        //write(GPIOA,  0x00);  // set port A off
        //write(MOTOR, 0x00);
        //write(IODIRA, 0);     // set port A as outputs
        //write(IODIRB, 0xFF);  // set port B as inputs
        //write(GPPUB,  0xFF);  // set port B pullups on
        
        int pins = 1;

        // infinite loop
        while(true) {
            
            // shift the bit to the left in the A register
            // this will cause the next LED to light up and 
            // the current LED to turn off.
            ///if(pins >= 255)
             /////   pins=1;
            write(MOTOR,  (byte)pins);  
            //pins = pins << 1;
            //Thread.sleep(1000);
            
            // read for input changes 
            //read(INPUT_PORT);
       }
	}
	
	public void read(byte register)
	{
        // send test ASCII message
        byte packet[] = new byte[3];
        packet[0] = READ_CMD;    // address byte
        packet[1] = register;    // register byte
        packet[2] = 0b00000000;  // data byte
           
	}
	
	public void write(byte register, int data)
	{
        // send test ASCII message
        byte packet[] = new byte[3];
        packet[0] = WRITE_CMD;  // address byte
        packet[1] = register;  // register byte
        packet[2] = (byte)data;  // data byte
           
        System.out.println("-----------------------------------------------");
        System.out.println("[TX] " + bytesToHex(packet));
        Spi.wiringPiSPIDataRW(0, packet, 3);
	}
	
    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }  
	
	
	public static void main(String args[]) throws InterruptedException
	{
		GoPiGo g = new GoPiGo();
	}
}
