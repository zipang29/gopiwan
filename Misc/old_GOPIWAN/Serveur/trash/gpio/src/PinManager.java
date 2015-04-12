package gpio.src;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinMode;

class PinManager{


	final GpioController gpio ;

	final Pin[] portCorrespondance= {null, null, RaspiPin.GPIO_08, 
			RaspiPin.GPIO_09, RaspiPin.GPIO_07,
			null, null, RaspiPin.GPIO_11, RaspiPin.GPIO_10, RaspiPin.GPIO_13,
			RaspiPin.GPIO_12, RaspiPin.GPIO_14, null, null, RaspiPin.GPIO_15,
			RaspiPin.GPIO_16, null, RaspiPin.GPIO_00, RaspiPin.GPIO_01, null,
			null, null, RaspiPin.GPIO_03, RaspiPin.GPIO_04, RaspiPin.GPIO_05,
			RaspiPin.GPIO_06, null, RaspiPin.GPIO_02, RaspiPin.GPIO_17, 
			RaspiPin.GPIO_18,
			RaspiPin.GPIO_19, RaspiPin.GPIO_20
	};

	GpioPinDigitalMultipurpose pin;
	GpioPinPwmOutput PWMpin;
	Pin pinNumber;

	public PinManager(int pinNumber){


		gpio = GpioFactory.getInstance();
		if ( portCorrespondance[pinNumber] == null){
			System.out.println("ERROR: pin "+pinNumber+" can't be controlled !");
		}
		else{
			this.pinNumber =  portCorrespondance[pinNumber] ;
			pin = gpio.provisionDigitalMultipurposePin(this.pinNumber, PinMode.DIGITAL_INPUT);
		}

		PWMpin = gpio.provisionPwmOutputPin(portCorrespondance[18]);
	}

	public void setAsInput(){
		pin.setMode(PinMode.DIGITAL_INPUT);
	}

	public void setAsOutput(){
		pin.setMode(PinMode.DIGITAL_OUTPUT);
	}

	public void setPin() {
		if(  pin.getMode() != PinMode.DIGITAL_OUTPUT ){
			System.out.println("Setting "+pinNumber+" as output");
			pin.setMode(PinMode.DIGITAL_OUTPUT);
		}
		System.out.println("settingPin");
		pin.high();
	}


	public void resetPin() {
		if(  pin.getMode() != PinMode.DIGITAL_OUTPUT ){
			System.out.println("Setting "+pinNumber+" as output");
			pin.setMode(PinMode.DIGITAL_OUTPUT);
		}
		System.out.println("resettingPin");
		pin.low();
	}


	public void togglePin() {
		if(  pin.getMode() != PinMode.DIGITAL_OUTPUT ){
			System.out.println("Setting "+pinNumber+" as output");
			pin.setMode(PinMode.DIGITAL_OUTPUT);
		}
		System.out.println("toggleingPin");
		pin.toggle();
	}


	public void setPwm(float value) {

		System.out.println("pwm on Pin");
		PWMpin.setPwm((int)(value*1024));

	}


	public boolean getState(){
		if(  pin.getMode() != PinMode.DIGITAL_INPUT ){
			System.out.println("Setting "+pinNumber+" as input");
			pin.setMode(PinMode.DIGITAL_INPUT);
		}
		System.out.println("get state");
		return pin.isHigh();
	}
	
	public int sizeCorrespondance()
	{
		return portCorrespondance.length;
	}
	
	
	
	
	public static void main(String args[])
	{
		PinManager pm = new PinManager(17);
		
		pm.setPin();
		
	
		
	}

}
