package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by 000066hs on 12/1/2015.
 */


/*
so, as far as i can tell the only issue we have now is that android studio won't generate an APK.
It keeps saying it's building the project but there's never an apk folder... ???
 */

public class BasicTeleop9618 extends OpMode {

/*
    // TETRIX VALUES.
    final static double ARM_MIN_RANGE  = 0.20;
    final static double ARM_MAX_RANGE  = 0.90;
    final static double CLAW_MIN_RANGE  = 0.20;
    final static double CLAW_MAX_RANGE  = 0.7;

    // position of the arm servo.
    double armPosition;

    // amount to change the arm servo position.
    double armDelta = 0.1;

    // position of the claw servo
    double clawPosition;

    // amount to change the claw servo position by
    double clawDelta = 0.1;
    */

    DcMotor motorRight;
    DcMotor motorLeft;
  //  DcMotor armPosition1;


    /**
     * Constructor
     */
    public BasicTeleop9618() {

    }

    /*
     * Code to run when the op mode is first enabled goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void init() {


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */

		/*
		 * For the demo Tetrix K9 bot we assume the following,
		 *   There are two motors "motor_1" and "motor_2"
		 *   "motor_1" is on the right side of the bot.
		 *   "motor_2" is on the left side of the bot and reversed.
		 *
		 * We also assume that there are two servos "servo_1" and "servo_6"
		 *    "servo_1" controls the arm joint of the manipulator.
		 *    "servo_6" controls the claw joint of the manipulator.
		 */
        motorRight = hardwareMap.dcMotor.get("motor_1");
        motorLeft = hardwareMap.dcMotor.get("motor_0");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
      //  armPosition1 = hardwareMap.dcMotor.get("servo_1");


        // assign the starting position of the wrist and claw
        //armPosition = 0.2;
        //clawPosition = 0.2;

    }

    /*
     * This method will be called repeatedly in a loop
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */
    @Override
    public void loop() {

		/*
		 * Gamepad 1
		 *
		 * Gamepad 1 controls the motors via the left stick, and it controls the
		 * wrist/claw via the a,b, x, y buttons
		 */

        // throttle: left_stick_y ranges from -1 to 1, where -1 is full up, and
        // 1 is full down
        // direction: left_stick_x ranges from -1 to 1, where -1 is full left
        // and 1 is full right
        float throttle = gamepad1.left_stick_y;
        float direction = gamepad1.right_stick_y;
        float left = throttle;
        float right = direction;
        //float armMovement1 = gamepad1.right_stick_y;
        telemetry.addData("right motor power", right);
        telemetry.addData("left motor power", left);
        //telemetry.addData("climber saver", armMovement1)


        // clip the right/left values so that the values never exceed +/- 1
          // right = Range.clip(right, -1, 1);             //ABANDONED
          // left = Range.clip(left, -1, 1);

        // scale the joystick value to make it easier to control
        //  the robot more precisely at slower speeds.                   //DONT FORGET
         // right = (float)scaleInput(right);                            //ABANDONED
       // left =  (float)scaleInput(left);

        // write the values to the motors and make sure values don't exceed '1'
       if (right > 1){
           right = 1;
           motorRight.setPower(right);
           motorLeft.setPower(left);
       }
        else if(right < -1){
           right = -1;
           motorRight.setPower(right);
           motorLeft.setPower(left);
       }
        else if(left > 1){
           left = 1;
           motorRight.setPower(right);
           motorLeft.setPower(left);
       }
        else if(left < -1){
           left = 1;
           motorRight.setPower(right);
           motorLeft.setPower(left);
       }
        else{
           motorRight.setPower(right);
           motorLeft.setPower(left);
       }
       //  armPosition1.setPower(armMovement1);

        // update the position of the arm.
        /*if (gamepad1.a) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
            armPosition += armDelta;
        }

        if (gamepad1.y) {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo.
            armPosition -= armDelta;
        }

        // update the position of the claw
        if (gamepad1.x) {
            clawPosition += clawDelta;
        }

        if (gamepad1.b) {
            clawPosition -= clawDelta;
        }

        // clip the position values so that they never exceed their allowed range.
        armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        clawPosition = Range.clip(clawPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);

        // write position values to the wrist and claw servo
        //arm.setPosition(armPosition);
        //claw.setPosition(clawPosition);




		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */
     //   telemetry.addData("Text", "*** Robot Data***");
       // telemetry.addData("arm", "arm:  " + String.format("%.2f", armPosition1));
        //telemetry.addData("claw", "claw:  " + String.format("%.2f", clawPosition));
       // telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
       // telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));

    }

    /*
     * Code to run when the op mode is first disabled goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
     */
    @Override
    public void stop() {

    }


    /*            ABANDONED
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
   /* double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24, 0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale*20;
    }
*/



}
