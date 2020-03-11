
/**
 *
 * @author GustavoPassos, Debora, Micaias
 */
public class Articulation {
        public String Member;
	public int RotationFlexion;
        public int RotationRequired;
	public int Voltage;
	
	public Articulation(String Member, int RotationFlexion, int Voltage) {
		this.Member = Member;
		this.RotationFlexion = RotationFlexion;
		this.Voltage = Voltage;
	}

	public String getMember() {
		return Member;
	}

	public void setMember(String member) {
		Member = member;
	}

	public int getVoltage() {
		return Voltage;
	}

	public void setVoltage(int voltage) {
		Voltage = voltage;
	}

	public int getRotationFlexion() {
		return RotationFlexion;
	}

	public void setRotationFlexion(int rotationFlexion) {
		RotationFlexion = rotationFlexion;
	}

        public int getRotationRequired() {
            return RotationRequired;
        }

        public void setRotationRequired(int RotationRequired) {
            this.RotationRequired = RotationRequired;
        }

	
}
