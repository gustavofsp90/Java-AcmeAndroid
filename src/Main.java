import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author GustavoPassos, Debora, Micaias
 */
public class Main {
    
	static Random random = new Random();
        static double RobotBattery = 8;
        //String to check the robot status
	static String RobotState;

	public static void main(String[] args) {
            
            //Creating a new instance for robot
		Robot robot = new Robot();
	
                int[] movementStand = {0,90,90,0,0,90,0,0,0};
                int[] movementStep = {15,0,45,0,45,0,45,15,0};
                int[] movementStep2 = {15,0,45,0,0,0,45,0,0};
                int[] movementSit = {0,90,90,0,0,90,0,0,0};

                //loading movements
                List movements = new ArrayList();
                movements.add(movementStand);
                movements.add(movementStep);
                movements.add(movementStep2);
                movements.add(movementStep);
                movements.add(movementSit);
                
                //starting a loop which never stop
                while (!movements.isEmpty()) {
                    
                    //getting next movement
                    robot.loadArticulationMovementRequired((int[]) movements.remove(0));
                    
                    //iterating th articulations
                    for(int i=0; i<robot.listArtic.size();i++){
                        if(robot.listArtic.get(i).getRotationRequired() == 0){
                            System.out.println(robot.listArtic.get(i).getMember()+" doesn't need to be moved...");
                        }else if(robot.listArtic.get(i).getRotationRequired() >= 15){
                            //if movement is more than 60% of available motion for a motor an additional 3 volts are required.
                            int extraVolt =0;
                            if(robot.listArtic.get(i).getRotationRequired() > robot.listArtic.get(i).getRotationFlexion()*0.6){
                                extraVolt = 3;
                            }
                            //once a motor is moved the Android requires 1 volt to hold all motors in place + 1
                            energyRequest(robot.listArtic.get(i).getVoltage()+1+extraVolt);
                            System.out.println("Moving "+robot.listArtic.get(i).getMember()+" 15 degrees...");
                            robot.listArtic.get(i).setRotationRequired(robot.listArtic.get(i).getRotationRequired() - 15);
                        }else{
                            //once a motor is moved the Android requires 1 volt to hold all motors in place + 1
                            energyRequest(robot.listArtic.get(i).getVoltage()+1);
                            System.out.println("Moving "+robot.listArtic.get(i).getMember()+" "+robot.listArtic.get(i).getRotationRequired()+" degrees...");
                            robot.listArtic.get(i).setRotationRequired(0);
                        }
                        //waiting one second to keep moving
                        try{
                            Thread.sleep(1000);
                        }catch(Exception ex){}
                    }//end for
                }//end while
	}

        /**
         * Managing the energy inside the robot battery
         * @param energyNeeded
         * @return 
         */
        public static int energyRequest(int energyNeeded){
            try{
                System.out.println("Required:"+energyNeeded+" charge:"+RobotBattery);
                
                //If the android is without power it will fall over and break and cost millions of euro to replace.
                if(RobotBattery<=0){
                    System.err.println("No battery energy,....robot falling... !!");
                    System.exit(0);
                }

                //enough power? 
                while(RobotBattery < energyNeeded || RobotBattery+2.6 <= 8){
                    //wait for power
                    System.out.println("More energy is required for battery, charging battery 2.6 volts...");
                    RobotBattery = RobotBattery+2.6;
                    Thread.sleep(1000);
                }

                System.out.println("Battery Loaded! keep moving...");
                RobotBattery = RobotBattery - energyNeeded;

            }catch(Exception ex){
                System.err.println("Error..."+ex.getMessage());
                ex.printStackTrace();
            }
            return 0;
        }
        
	private static Runnable ThreadRobot = new Runnable() {
		
		@Override
		public void run() {
			Thread.State state = Thread.currentThread().getState();  //Starting a thread
			System.out.println("Robot is walking.");//Displating on the console
		}
		
	};
	
}
