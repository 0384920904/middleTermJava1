package Model;

public class SinhVien {
	private String id;
	private String hoVaTen;
	private String queQuan;
	private String sex;
	private float diem;

	public SinhVien() {
	}

	public SinhVien(String id, String hoVaTen, String queQuan,String sex, float diem) {
		this.id = id;
		this.hoVaTen = hoVaTen;
		this.queQuan = queQuan;
		this.sex=sex;
		this.diem = diem;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public float getDiem() {
		return diem;
	}

	public void setDiem(float diem) {
		this.diem = diem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
