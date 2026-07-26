# 💼 Accounting Management System - Enterprise Finance & Transaction Dashboard

Language / Dil: **[🇬🇧 English](#-accounting-management-system---enterprise-finance--transaction-dashboard)** | **[🇹🇷 Türkçe](#-türkçe)**

---

A Java Swing enterprise application designed for corporate accounting, transaction tracking, role-based user management, and financial data visualization. Built using **FlatLaf**, **jBCrypt**, **JFreeChart**, and **MySQL**.

---

## 🌟 Key Features

- 🎨 **Modern FlatLaf UI & Theme Engine:** Supports dark and light themes with FlatLaf look-and-feel customization.
- 🔒 **BCrypt Password Hashing:** Secure authentication mechanism leveraging `jBCrypt` password hashing.
- 📊 **Interactive Financial Charts:** Dynamic revenue, expenditure, and balance analytics powered by `JFreeChart`.
- 👑 **Role-Based Access Control (RBAC):** Distinct dashboards for Administrators (`Admin_Screen`) and General Staff (`User_Screen`).
- 💸 **Transaction Management:** Real-time income/expense logging, category filtering, batch updates, and JSON data export/import (`Converter.java`).
- 🗄️ **Database Persistence:** Pre-configured MySQL schema with sample data backup (`backup.sql`).

---

## 📦 Tech Stack

- **GUI Framework:** Java Swing + FlatLaf 3.6
- **Security:** jBCrypt 0.4
- **Data Visualization:** JFreeChart 1.5.3
- **Data Serialization:** org.json
- **Database:** MySQL Server 8.0+ (mysql-connector-j 9.3.0)
- **Build Tool:** Apache Maven

---

## 🚀 Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/caglaryilmaz987/Accounting_Manegement_System.git
   cd Accounting_Manegement_System
   ```

2. **Database Setup:**
   Import `backup.sql` into your local MySQL server:
   ```bash
   mysql -u root -p < backup.sql
   ```

3. **Build & Run via Maven:**
   ```bash
   mvn clean compile exec:java -Dexec.mainClass="com.mycompany.accaunting_manegment_system.Start"
   ```

---

<br/>

# 🇹🇷 Türkçe

[⬆️ Back to English](#-accounting-management-system---enterprise-finance--transaction-dashboard)

## 💼 Muhasebe ve Finans Yönetim Sistemi

Bu proje, **Java Swing**, **FlatLaf**, **jBCrypt**, **JFreeChart** ve **MySQL** altyapısı kullanılarak geliştirilmiş kurumsal muhasebe, gelir/gider takibi ve rol tabanlı kullanıcı yönetim sistemidir.

---

### 🌟 Öne Çıkan Özellikler

- 🎨 **Modern FlatLaf Arayüzü:** Koyu (Dark) ve Açık (Light) tema desteği.
- 🔒 **BCrypt Şifreleme:** Güvenli kullanıcı kimlik doğrulama.
- 📊 **Finansal Grafik Analitiği:** JFreeChart ile gelir, gider ve net bakiye grafikleri.
- 👑 **Rol Tabanlı Yetkilendirme:** Yönetici (`Admin_Screen`) ve Kullanıcı (`User_Screen`) için ayrı paneller.
- 💸 **İşlem Yönetimi:** Gelir/gider kaydı, kategori filtreleme ve JSON verilerini dışa/içe aktarma.
- 🗄️ **Veritabanı Desteği:** İçe aktarılabilir MySQL yedek dosyası (`backup.sql`).

---

### 📜 Lisans

Bu proje MIT lisansı altındadır.
