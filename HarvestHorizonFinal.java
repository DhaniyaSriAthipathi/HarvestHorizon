//package HarvestHorizon.java;

import java.util.*;

class Crop {
    private String name;
    private String season;
    private double todayPrice;
    private double yesterdayPrice;
    private String disease;
    private double rainAffectedLand;
    private double compensation;

    public Crop(String name, String season, double todayPrice, double yesterdayPrice) {
        this.name = name;
        this.season = season;
        this.todayPrice = todayPrice;
        this.yesterdayPrice = yesterdayPrice;
        this.disease = "None";
        this.rainAffectedLand = 0;
        this.compensation = 0;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setRainAffectedLand(double land) {
        this.rainAffectedLand = land;
        this.compensation = land * 5000; // ₹5000 per acre
    }

    public String getName() { return name; }
    public String getSeason() { return season; }
    public double getTodayPrice() { return todayPrice; }
    public double getYesterdayPrice() { return yesterdayPrice; }
    public String getDisease() { return disease; }
    public double getCompensation() { return compensation; }

    public void generateReport(Map<String, String> diseaseInfo, String farmerName) {
        System.out.println("\n===== 🌾 HarvestHorizon Report =====");
        System.out.println("👨‍🌾 Farmer Name: " + farmerName);
        System.out.println("🌱 Crop: " + name + " (" + season + ")");
        System.out.println("💰 Today’s Price: ₹" + todayPrice + "/kg | Yesterday: ₹" + yesterdayPrice + "/kg");

        if (todayPrice > yesterdayPrice) {
            System.out.println("📈 Price Change: +₹" + (todayPrice - yesterdayPrice) + " (Profit ↑)");
        } else if (todayPrice < yesterdayPrice) {
            System.out.println("📉 Price Change: -₹" + (yesterdayPrice - todayPrice) + " (Loss ↓)");
        } else {
            System.out.println("⚖ No price change compared to yesterday.");
        }

        if (!disease.equals("None")) {
            System.out.print("🦠 Disease: " + disease);
            if (diseaseInfo.containsKey(disease)) {
                System.out.println(" → 💊 Suggestion: " + diseaseInfo.get(disease));
            } else {
                System.out.println(" → 💊 No suggestion available in database.");
            }
        } else {
            System.out.println("🦠 No disease reported");
        }

        if (rainAffectedLand > 0) {
            System.out.println("🌧 Rainfall Compensation: ₹" + compensation +
                    " (Affected: " + rainAffectedLand + " acres)");
        } else {
            System.out.println("🌧 No rainfall damage reported");
        }

        System.out.println("-------------------------------------");
    }
}

public class HarvestHorizonFinal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Crop> crops = new ArrayList<>();

        // Disease info database
        Map<String, String> diseaseInfo = new HashMap<>();
        diseaseInfo.put("Rice Blast", "Use Tricyclazole fungicide");
        diseaseInfo.put("Wheat Rust", "Apply Mancozeb spray");
        diseaseInfo.put("Cotton Wilt", "Use Carbendazim treatment");
        diseaseInfo.put("Sugarcane Red Rot", "Use Bavistin fungicide");
        diseaseInfo.put("Maize Leaf Blight", "Spray Zineb or Mancozeb");
        diseaseInfo.put("Soybean Rust", "Use Propiconazole fungicide");
        diseaseInfo.put("Groundnut Tikka Disease", "Spray Chlorothalonil");

        // Crop-wise disease mapping
        Map<String, String> cropDiseaseMap = new HashMap<>();
        cropDiseaseMap.put("Rice", "Rice Blast");
        cropDiseaseMap.put("Wheat", "Wheat Rust");
        cropDiseaseMap.put("Cotton", "Cotton Wilt");
        cropDiseaseMap.put("Sugarcane", "Sugarcane Red Rot");
        cropDiseaseMap.put("Maize", "Maize Leaf Blight");
        cropDiseaseMap.put("Soybean", "Soybean Rust");
        cropDiseaseMap.put("Groundnut", "Groundnut Tikka Disease");

        // Crop-wise seasons
        Map<String, List<String>> cropSeasons = new HashMap<>();
        cropSeasons.put("Rice", Arrays.asList("Kharif", "Rabi", "Summer"));
        cropSeasons.put("Wheat", Arrays.asList("Rabi", "Spring", "Summer"));
        cropSeasons.put("Cotton", Arrays.asList("Kharif", "Summer", "Autumn"));
        cropSeasons.put("Sugarcane", Arrays.asList("Annual", "Spring", "Autumn"));
        cropSeasons.put("Maize", Arrays.asList("Kharif", "Rabi", "Summer"));
        cropSeasons.put("Soybean", Arrays.asList("Kharif", "Rabi", "Summer"));
        cropSeasons.put("Groundnut", Arrays.asList("Kharif", "Rabi", "Summer"));

        // Crop price database (simulated real-world data)
        Map<String, Double> todayPrices = new HashMap<>();
        Map<String, Double> yesterdayPrices = new HashMap<>();

        todayPrices.put("Rice", 32.5);
        yesterdayPrices.put("Rice", 31.8);

        todayPrices.put("Wheat", 28.0);
        yesterdayPrices.put("Wheat", 27.5);

        todayPrices.put("Cotton", 62.0);
        yesterdayPrices.put("Cotton", 60.0);

        todayPrices.put("Sugarcane", 4.0);
        yesterdayPrices.put("Sugarcane", 3.8);

        todayPrices.put("Maize", 25.5);
        yesterdayPrices.put("Maize", 26.0);

        todayPrices.put("Soybean", 52.5);
        yesterdayPrices.put("Soybean", 51.0);

        todayPrices.put("Groundnut", 60.0);
        yesterdayPrices.put("Groundnut", 59.5);

        // Start input
        System.out.print("Enter Farmer Name: ");
        String farmerName = sc.nextLine();
        System.out.print("Enter Land Size (in acres): ");
        double landSize = sc.nextDouble();
        sc.nextLine();

        System.out.println("\n--- 🌾 Welcome to HarvestHorizon 🌾 ---");

        boolean addMore = true;
        while (addMore) {
            System.out.println("\nSelect Crop:");
            String[] cropList = {"Rice", "Wheat", "Cotton", "Sugarcane", "Maize", "Soybean", "Groundnut"};
            for (int i = 0; i < cropList.length; i++) {
                System.out.println((i + 1) + ". " + cropList[i]);
            }
            System.out.print("Enter your choice (1-7 or -1 to stop): ");
            int cropChoice = sc.nextInt();
            if (cropChoice == -1) break;
            sc.nextLine();

            String cropName = cropList[cropChoice - 1];

            // Select season
            System.out.println("\nSelect Season for " + cropName + ":");
            List<String> seasons = cropSeasons.get(cropName);
            for (int i = 0; i < seasons.size(); i++) {
                System.out.println((i + 1) + ". " + seasons.get(i));
            }
            System.out.print("Enter your choice (1-3): ");
            int seasonChoice = sc.nextInt();
            sc.nextLine();
            String season = seasons.get(seasonChoice - 1);

            // Automatically get prices
            double todayPrice = todayPrices.get(cropName);
            double yesterdayPrice = yesterdayPrices.get(cropName);
            System.out.println("💰 Today’s Price for " + cropName + ": ₹" + todayPrice + "/kg");
            System.out.println("💰 Yesterday’s Price: ₹" + yesterdayPrice + "/kg");

            Crop crop = new Crop(cropName, season, todayPrice, yesterdayPrice);

            // Disease check (auto-detect)
            System.out.print("Is the crop affected by any disease? (yes/no): ");
            String diseaseAns = sc.nextLine();
            if (diseaseAns.equalsIgnoreCase("yes")) {
                String disease = cropDiseaseMap.get(cropName);
                crop.setDisease(disease);
                System.out.println("🦠 Disease detected: " + disease);
            }

            // Rainfall check
            System.out.print("Is the crop affected by rain? (yes/no): ");
            String rainAns = sc.nextLine();
            if (rainAns.equalsIgnoreCase("yes")) {
                System.out.print("Enter how much land (in acres) was affected: ");
                double affectedLand = sc.nextDouble();
                sc.nextLine();
                crop.setRainAffectedLand(affectedLand);
            }

            // Generate report
            crop.generateReport(diseaseInfo, farmerName);
            crops.add(crop);

            System.out.print("\nDo you want to enter another crop? (yes/no): ");
            String more = sc.nextLine();
            addMore = more.equalsIgnoreCase("yes");
        }

        // Final Summary
        System.out.println("\n🌿 ===== FINAL SUMMARY ===== 🌿");
        System.out.println("👨‍🌾 Farmer: " + farmerName + " | Land: " + landSize + " acres");

        double totalCompensation = 0;
        int profitCount = 0, lossCount = 0;
        int i = 1;

        for (Crop c : crops) {
            double priceDiff = c.getTodayPrice() - c.getYesterdayPrice();
            if (priceDiff > 0) profitCount++;
            else if (priceDiff < 0) lossCount++;

            System.out.println(i + "⃣ " + c.getName() + " → " +
                    (priceDiff > 0 ? "Profit ↑ ₹" + priceDiff + "/kg"
                            : priceDiff < 0 ? "Loss ↓ ₹" + (-priceDiff) + "/kg"
                            : "No Change") +
                    " | Compensation: ₹" + c.getCompensation());
            totalCompensation += c.getCompensation();
            i++;
        }

        System.out.println("\n📊 TOTAL Compensation: ₹" + totalCompensation);
        System.out.println("📈 Crops in Profit: " + profitCount);
        System.out.println("📉 Crops in Loss: " + lossCount);

        // Market Price Table
        System.out.println("\n===== 🏬 Market Price Table =====");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Crop", "Yesterday(₹/kg)", "Today(₹/kg)", "Change");
        System.out.println("------------------------------------------------------------");
        for (Crop c : crops) {
            double change = c.getTodayPrice() - c.getYesterdayPrice();
            String changeText = (change > 0 ? "↑ +₹" + change : change < 0 ? "↓ -₹" + (-change) : "No Change");
            System.out.printf("%-15s %-15.2f %-15.2f %-15s%n",
                    c.getName(), c.getYesterdayPrice(), c.getTodayPrice(), changeText);
        }

        System.out.println("\n🌱 Thank you for visiting HARVESTHORIZON 🌱");
        System.out.println("🌍 Empowering Farmers with Knowledge & Growth 🌍");

        sc.close();
    }
}