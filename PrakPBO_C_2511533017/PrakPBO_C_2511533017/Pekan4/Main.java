package Pekan4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor tunai");
			System.out.println("3. Tarik tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("6. Cetak Mutasi (Riwayat)");
			System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
			System.out.println("0. Keluar");
			System.out.print("Pilih Menu : ");
			
			int pilihan = input.nextInt();
			input.nextLine();
			
			switch (pilihan) {
				case 1:
					
					System.out.print("Masukkan Nomor Rekening : ");
					String nomor = input.nextLine();

					System.out.print("Masukkan Nama Pemilik : ");
					String nama = input.nextLine();

					System.out.print("Masukkan Saldo Awal : ");
					double saldoAwal = input.nextDouble();
					input.nextLine(); 


					
					String pinAwal;
					while (true) {
					    System.out.print("Buat PIN (6 digit angka) : ");
					    pinAwal = input.nextLine();

					    if (pinAwal.matches("\\d{6}")) {
					        break;
					    }

					    System.out.println("PIN harus terdiri dari 6 digit angka!");
					}


					
					System.out.println("Pilih Produk : 1. Tabungan Umum | 2. Giro Bisnis");
					System.out.print("Masukkan pilihan produk : ");
					int produk = input.nextInt();
					input.nextLine(); 


					
					if (produk == 1) {

					    System.out.print("Masukkan Suku Bunga (dalam persen) : ");
					    double sukuBunga = input.nextDouble();
					    input.nextLine();

					    akunAktif = new RekeningTabungan(nomor, nama, saldoAwal, pinAwal, sukuBunga);

					} else if (produk == 2) {

					    System.out.print("Masukkan Batas Overdraft (limit pinjaman) : ");
					    double batasOverdraft = input.nextDouble();
					    input.nextLine();

					    akunAktif = new RekeningGiro(nomor, nama, saldoAwal, pinAwal, batasOverdraft);

					} else {

					    System.out.println("Pilihan produk tidak tersedia. Rekening gagal dibuat.");
					    break;
					}
					 daftarRekening.add(akunAktif);

					    break;
					
				case 2:
					if (akunAktif == null) {
						System.out.println("Error: Mohon maaf, anda belum memiliki nomor rekening / belum login!");
					} else {
						System.out.print("Masukan nominal setor: ");
						double setor = input.nextDouble();
						akunAktif.setorTunai(setor);
					}
					break;
					
				case 3:
					if (akunAktif == null) {
						System.out.println("Error: Mohon maaf, anda belum memiliki nomor rekening / belum login!");
					
					} else {
						System.out.print("Masukan pin: ");
						String pin = input.nextLine();
					if (akunAktif.otentikasi(pin)) {
						System.out.print("Masukan nominal penarikan: ");
						double tarik = input.nextDouble();
						akunAktif.tarikTunai(tarik);
						
					} else { 
						System.out.println("Akses Ditolak : PIN yang Anda masukan salah!");
						
						}
					}
					break;
					
				case 4:
					if (akunAktif == null) {
						System.out.println("Error : anda belum membuka rekening / belum login");
					} else {
						akunAktif.cekInformasi();
					}
					break;
					
				case 5:
					if (daftarRekening.isEmpty()) {
						System.out.println("Belum ada rekening yang terdaftar. Silakan buka rekening baru terlebih dahulu.");
					} else {
						System.out.print("Masukan nomor rekening yang ingin diaktifkan: ");
						String noTujuan = input.nextLine();
						boolean ditemukan = false;
						
						for (Rekening r : daftarRekening) {
							if (r.getNomorRekening().equals(noTujuan)) {
								akunAktif = r;
								ditemukan = true;
								System.out.println("Berhasil ganti akun!");
								break;
							}
						}
						
						if (!ditemukan) {
							System.out.println("Nomor rekening tidak ditemukan!");
						}
					}
					break;

				case 6:
					if (akunAktif == null) {
						System.out.println("Error : anda belum membuka rekening / belum login");
					} else {
						System.out.print("Masukan pin: ");
						String pin = input.nextLine();
					if (akunAktif.otentikasi(pin)) {
						akunAktif.cetakMutasi();
						
					} else { 
						System.out.println("Akses Ditolak : PIN yang Anda masukan salah!");
						
					}
					}
					break;
					
				case 7:

				    if (akunAktif == null) {

				        System.out.println("Belum ada rekening yang dibuat!");
				        break;
				        
				    }				    
				    if (akunAktif instanceof RekeningTabungan) {
    
				        RekeningTabungan tabungan = (RekeningTabungan) akunAktif;
				        tabungan.tambahBungaAkhirBulan();
				        System.out.println("Bunga akhir bulan berhasil ditambahkan.");
				    } else {
				        System.out.println(
				            "Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan."
				        );

				    }

				    break;
					
				case 0:
					isRunning = false;
					System.out.println("Sistem ditutup. Terima kasih!");
					break;
					
				default:
					System.out.println("Pilihan tidak valid");
			}
		}
		

		input.close();
	}
}