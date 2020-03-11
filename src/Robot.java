import java.util.ArrayList;
/**
 *
 * @author GustavoPassos, Debora, Micaias
 */
public class Robot {
	public ArrayList<Articulation> listArtic = new ArrayList<Articulation>();
	public int Volts;
	
	public Robot() {
		Volts = 4;
		listArtic.add(new Articulation("Ankle", 30, 3));
		listArtic.add(new Articulation("Knee", 90, 3));
		listArtic.add(new Articulation("Hip", 90, 4));
		listArtic.add(new Articulation("Waist", 120, 4));
		listArtic.add(new Articulation("Wrist", 180, 2));
		listArtic.add(new Articulation("Elbow", 140, 3));
		listArtic.add(new Articulation("Shoulder", 540, 2));
		listArtic.add(new Articulation("Neck", 30, 3));
		listArtic.add(new Articulation("Head", 360, 3));
	}
        
        /**
         * Setting rotation degrees required for each movement
         * @param req int array toqith degrees needed to move android
         */
        public void loadArticulationMovementRequired(int[] req){
            for(int i=0; i< req.length; i++){
                listArtic.get(i).setRotationRequired(req[i]);
            }
        }
}
