package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * [{@link WalletsTest}]
 *
 * TODO :
 * - Buatlah sebuah class WalletsTest yang berfungsi untuk melakukan pengujian terhadap class Wallets.
 * - Satu method test untuk satu skenario pengujian.
 */
class WalletsTest {

    private final String owner = "Erge";
    private final String errMsgOwnerInit = "inisiasi owner tidak valid";

    private Wallets createTestWallet() {
        return new Wallets(owner);
    }

    /**
     * [{@link Wallets#setOwner(String)}]
     *
     * <br><br>
     *
     * Melakukan tes apakah method setOwner dapat merubah owner dengan benar. <br>
     * Method [{@link Wallets#setOwner(String)}] akan melakukan tes sebagai berikut: <br>
     *  <ol>
     *      <li>apakah owner telah terinisiasi terlebih dahulu </li>
     *      <li>melakukan tes untuk [{@link Wallets#setOwner(String)}] apakah berhasil merubah owner dengan benar.</li>
     *      <li>Melakukan tes apakah jika [{@link Wallets#setOwner(String)}] diberikan "" akan mengubah owner menjadi "" </li>
     *      <li>apakah owner tidak null setelah [{@link Wallets#owner}] diganti ""</li>
     *  </ol>
     */
    @Test
    void testSetOwner() {
        final Wallets testWallet = createTestWallet();

        final String errMsg = "Set owner tidak merubah owner dengan benar";

        final MyTestUtilities utils = new MyTestUtilities("testSetOwner");
        assertAll(
                () -> {
                    assertEquals(owner, testWallet.owner, errMsgOwnerInit);
                    utils.printSuccessMessage("inisiasi owner", owner);
                },
                () -> {
                    final String newOwner = "Owner Baru";
                    testWallet.setOwner(newOwner);
                    assertEquals(newOwner, testWallet.owner, errMsg);
                    utils.printSuccessMessage("setOwner", newOwner);
                },
                () -> {
                    final String newOwner = "";
                    testWallet.setOwner(newOwner);
                    assertEquals(newOwner, testWallet.owner, errMsg + " (owner kosong)");
                    utils.printSuccessMessage("setOwner", newOwner);
                },
                () -> {
                    assertNotNull(testWallet.owner, errMsg);
                    utils.printSuccessMessage("setOwner", testWallet.owner);
                }
        );

        utils.endTest();
    }

    /**
     * [{@link Wallets#tambahKartu(String)}]
     * <br><br>
     * Melakukan tes apakah method tambahKartu dapat menambahkan kartu dengan benar. <br>
     * Method [{@link Wallets#tambahKartu(String)}] akan melakukan tes sebagai berikut: <br>
     * <ol>
     *     <li>apakah kartu berhasil ditambahkan</li>
     *     <li>apakah kartu berhasil ditambahkan lagi</li>
     * </ol>
     */
    @Test
    void testTambahKartu() {
        final Wallets testWallet = createTestWallet();

        final String cardName1 = "Kartu Baru Pertama";
        final String cardName2 = "Kartu Baru Kedua";

        final MyTestUtilities utils = new MyTestUtilities("testTambahKartu");
        assertAll(
                () -> {
                    testWallet.tambahKartu(cardName1);
                    assertTrue(
                            testWallet.listKartu.contains(cardName1),
                            "Kartu "+ cardName1 +"tidak ditambahkan");
                    utils.printSuccessMessage("tambahKartu", "size: " + testWallet.listKartu.size());
                },
                () -> {
                    testWallet.tambahKartu(cardName2);
                    assertTrue(
                            testWallet.listKartu.contains(cardName2),
                            "Kartu "+ cardName2 +"tidak ditambahkan");
                    utils.printSuccessMessage("tambahKartu", "size: " + testWallet.listKartu.size());
                }
        );
        utils.endTest();


    }

    /**
     * [{@link Wallets#tambahKartu(String)}]
     * <br><br>
     *
     * Melakukan tes apakah jika method tambahKartu diberikan null, maka tidak akan menambahkan kartu.
     */
    @Test
    void testTambahKartuNull() {
        final Wallets testWallet = createTestWallet();
        final int expectedSize = testWallet.listKartu.size();

        final MyTestUtilities utils = new MyTestUtilities("testTambahKartuNull");

        testWallet.tambahKartu(null);
        assertEquals(
                expectedSize,
                testWallet.listKartu.size(),
                "Operasi not null tidak berjalan " +  testWallet.listKartu);
        utils.printSuccessMessage("tambahKartu", "size: " + expectedSize);

        utils.endTest();
    }

    /**
     * [{@link Wallets#ambilKartu(String)}]
     * <br><br>
     * Melakukan tes apakah method ambilKartu dapat mengambil kartu dengan benar. <br>
     * Method [{@link Wallets#ambilKartu(String)}] akan melakukan tes sebagai berikut: <br>
     * <ol>
     *     <li>apakah kartu berhasil diambil</li>
     *     <li>apakah kartu berhasil diambil lagi</li>
     * </ol>
     *
     * <br><br>
     * Catatan: Sebelum mengambil kartu diberikan 2 kartu terlebih dahulu.
     */
    @Test
    void testAmbilKartu() {
        final Wallets testWallet = createTestWallet();

        final String cardName1 = "Kartu Baru Pertama";
        final String cardName2 = "Kartu Baru Kedua";

        testWallet.tambahKartu(cardName1);
        testWallet.tambahKartu(cardName2);

        int currentSize = testWallet.listKartu.size();

        final MyTestUtilities utils = new MyTestUtilities("testAmbilKartu");

        assertAll(
                () -> {
                    final String card = testWallet.ambilKartu(cardName1);
                    assertEquals(
                            cardName1,
                            card,
                            "Kartu "+ cardName1 +"tidak diambil");
                    utils.printSuccessMessage("ambilKartu", cardName1);

                    assertTrue(currentSize > testWallet.listKartu.size(), "Kartu tidak terhapus");
                    utils.printSuccessMessage("ambilKartu", "size: " + testWallet.listKartu.size());
                },
                () -> {
                    final String card = testWallet.ambilKartu(cardName2);
                    assertEquals(
                            cardName2,
                            card,
                            "Kartu "+ cardName2 +"tidak diambil");
                    utils.printSuccessMessage("ambilKartu", cardName2);

                    assertTrue(currentSize > testWallet.listKartu.size(), "Kartu tidak terhapus");
                    utils.printSuccessMessage("ambilKartu", "size: " + testWallet.listKartu.size());
                }
        );

        utils.endTest();
    }

    /**
     * [{@link Wallets#ambilKartu(String)}]
     * <br><br>
     * Melakukan tes apakah jika method ambilKartu diberikan kartu yang tidak ada, maka akan mengembalikan null.
     */
    @Test
    void testAmbilKartuBerlebih() {
        final Wallets testWallet = createTestWallet();

        final String cardName1 = "Kartu Baru Pertama";
        final String cardName2 = "Kartu Baru Kedua";

        testWallet.tambahKartu(cardName1);

        int currentSize = testWallet.listKartu.size();

        final MyTestUtilities utils = new MyTestUtilities("testAmbilKartuBerlebih");

        assertAll(
                () -> {
                    final String card = testWallet.ambilKartu(cardName1);
                    assertEquals(
                            cardName1,
                            card,
                            "Kartu "+ cardName1 +"tidak diambil");
                    utils.printSuccessMessage("ambilKartu", cardName1);

                    assertTrue(currentSize > testWallet.listKartu.size(), "Kartu tidak terhapus");
                    utils.printSuccessMessage("ambilKartu", "size: " + testWallet.listKartu.size());
                },
                () -> {
                    final String card = testWallet.ambilKartu(cardName2);
                    assertNull(
                            card,
                            "Kartu "+ cardName2 +"tidak null");
                    utils.printSuccessMessage("ambilKartuImajiner", null);
                }
        );

        utils.endTest();
    }

    @Test
    void testTambahUangRupiahDibawahSeribu() {
        final Wallets testWallet = createTestWallet();
        final int uangPertama = 738;
        final int uangKedua = 1000;
        final int uangKetiga = -21386;

        final MyTestUtilities utils = new MyTestUtilities("testTambahUangRupiahDibawahSeribu");

        assertAll(
                () -> {
                    testWallet.tambahUangRupiah(uangPertama);
                    assertEquals(
                            uangPertama,
                            testWallet.listUangKoin.get(0),
                            "Uang tidak ditambahkan");
                    utils.printSuccessMessage("tambahUangRupiah", "addKoin: " + uangPertama);

                    testWallet.tambahUangRupiah(uangKedua);
                    assertEquals(
                            uangKedua,
                            testWallet.listUangKoin.get(1),
                            "Uang tidak ditambahkan");
                    utils.printSuccessMessage("tambahUangRupiah", "addKoin: " + uangKedua);
                },
                () -> {
                    final int expectedSize = testWallet.listUangKoin.size();
                    testWallet.tambahUangRupiah(0);
                    assertEquals(
                            expectedSize,
                            testWallet.listUangKoin.size(),
                            "Uang tidak ditambahkan");
                    utils.printSuccessMessage("tambahUangRupiah | tes tambah uang 0");
                },
                () -> {
                    assertThrows(
                            Error.class,
                            () -> testWallet.tambahUangRupiah(uangKetiga),
                            "Uang negatif tidak dihandle");
                    utils.printSuccessMessage("tambahUangRupiah | tes tambah uang negatif");
                }
        );

        utils.endTest();
    }

    @Test
    void testTambahUangRupiahDiatasSeribu() {
        final Wallets testWallet = createTestWallet();
        final int uangPertama = 1000;
        final int uangKedua = 50_0051;

        final MyTestUtilities utils = new MyTestUtilities("testTambahUangRupiahDiatasSeribu");

        assertAll(
                () -> {
                    final int expectedSize = testWallet.listUangLembaran.size();
                    testWallet.tambahUangRupiah(uangPertama);
                    assertEquals(
                            expectedSize,
                            testWallet.listUangLembaran.size(),
                            "Uang tidak ditambahkan");
                    utils.printSuccessMessage("tambahUangRupiah | tes tambah uang 1000");
                },
                () -> {
                    testWallet.tambahUangRupiah(uangKedua);
                    assertEquals(
                            uangKedua,
                            testWallet.listUangLembaran.get(0),
                            "Uang tidak tertambahkan");
                    utils.printSuccessMessage("tambahUangRupiah | tes tambah uang 50_0051");
                }

        );

        utils.endTest();
    }



    @Test
    void testAmbilUangRupiahTersedia() {
        final Wallets testWallet = createTestWallet();
        final int uangPertama = 1000;
        final int uangKedua = 50_0051;

        testWallet.tambahUangRupiah(uangPertama);
        testWallet.tambahUangRupiah(uangKedua);

        final MyTestUtilities utils = new MyTestUtilities("testAmbilUangRupiahTersedia");

        assertAll(
                () -> {
                    final int uang = testWallet.ambilUangRupiah(uangPertama);
                    assertEquals(
                            uangPertama,
                            uang,
                            "Uang tidak diambil");
                    utils.printSuccessMessage("ambilUangRupiah", "ambil: " + uangPertama);
                },
                () -> {
                    final int uang = testWallet.ambilUangRupiah(uangKedua);
                    assertEquals(
                            uangKedua,
                            uang,
                            "Uang tidak diambil");
                    utils.printSuccessMessage("ambilUangRupiah", "ambil: " + uangKedua);
                }
        );

        utils.endTest();

    }

    @Test
    void testAmbilUangRupiahTidakTersedia() {
        final Wallets testWallet = createTestWallet();
        final int uangPertama = 1000;
        final int uangKedua = 50_0051;

        testWallet.tambahUangRupiah(uangPertama);

        final MyTestUtilities utils = new MyTestUtilities("testAmbilUangRupiahTidakTersedia");

        assertAll(
                () -> {
                    final int uang = testWallet.ambilUangRupiah(uangKedua);
                    assertEquals(
                            0,
                            uang,
                            "Uang tidak diambil");
                    utils.printSuccessMessage("ambilUangRupiah", "ambil: " + uangKedua);
                }
        );

        utils.endTest();
    }

    @Test
    void testTampilkanUang() {
        final Wallets testWallet = createTestWallet();
        final int uangPertama = 1000;
        final int uangKedua = 50_0051;
        final int uangKetiga = Integer.MAX_VALUE;

        testWallet.tambahUangRupiah(uangPertama);
        testWallet.tambahUangRupiah(uangKedua);

        final MyTestUtilities utils = new MyTestUtilities("testTampilkanUang");

        assertAll(
                () -> {
                    final int expectedTotal = uangPertama + uangKedua;
                    assertEquals(
                            expectedTotal,
                            testWallet.tampilkanUang(),
                            "Total uang tidak sesuai");
                    utils.printSuccessMessage("tampilkanUang", "total: " + expectedTotal);
                },
                () -> {
                    testWallet.tambahUangRupiah(uangKetiga);
                    assertThrows(
                            Error.class,
                            () -> testWallet.tampilkanUang(),
                            "Total uang tidak sesua, total :" + testWallet.tampilkanUang());
                    utils.printSuccessMessage("tampilkanUang", "Uang lebih dari Integer.MAX_VALUE terhandle");
                }
        );

        utils.endTest();
    }
}