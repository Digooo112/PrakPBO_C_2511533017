package Pekan4;

public class RekeningTabungan extends Rekening {
	
	//Atribut spesifik yang hanya dimiliki oleh tabungan 
	private double sukuBunga;
	
	// Counstructor Subclass
	public RekeningTabungan(String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
		super(nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
	}
	public double getSukuBunga() {return sukuBunga; }
	
	public void tambahBungaAkhirBulan() {
		double nominalBunga = saldo * (sukuBunga / 100);
		saldo += nominalBunga;
		
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi(idTrx, "Bunga", nominalBunga));
		
		System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: Rp" + nominalBunga);
	}
	
	

}
