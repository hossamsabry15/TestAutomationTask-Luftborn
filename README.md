# **eBay Search Automation Test**

## **Quick Start**
```bash
# 1. Download project
git clone https://github.com/hossamsabry15/TestAutomationTask-Luftborn.git

# 2. Go to folder
cd TestAutomationTask-Luftborn

# 3. Run test
mvn clean test
```

## **What It Does**
✅ Opens Chrome browser  
✅ Goes to eBay.com  
✅ Searches for "mazda mx-5"  
✅ Shows results  
✅ Applies "Manual" filter  
✅ Closes browser

**Test Time:** 31 seconds  
**Status:** ✅ PASSED (1/1 tests passed)

## **📊 Allure Report Included**
The project includes **Allure Reports** - beautiful test reports that show:
- Test steps with timing
- Screenshots (if added)
- Pass/fail status
- Execution timeline

### **View Allure Report:**
```bash
# Run tests and generate report
mvn clean test allure:report

# Open the report (requires Allure installed)
allure serve target/allure-results
```

**Report shows:**
- ✅ Test steps with exact timings
- 📈 31.4 seconds total execution
- 🔍 Each action duration (search took 364ms, etc.)
- 📋 Test execution details

## **Requirements**
- Java 11 or higher
- Maven
- Chrome browser
- **Allure** (for reports, optional)

## **Project Files**
```
TestAutomationTask-Luftborn/
├── src/              # Code files
├── pom.xml          # Setup file
├── allure-results/  # Test reports (auto-generated)
└── README.md        # This file
```

## **Test Results**
- **Test Name:** Ebay Search Functionality Test
- **Duration:** 31 seconds 394ms
- **Result:** ✅ PASS
- **Report:** Allure report available

## **Steps Tested**
1. Open eBay homepage (3.1s)
2. Check page title (25ms)
3. Search for "mazda mx-5" (364ms)
4. Verify results (1.6s)
5. Apply filter (208ms)
6. Close browser

## **Install Allure (Optional)**
To see the fancy reports, install Allure:

## **Need Help?**
If test fails:
1. Update Chrome browser
2. Check internet connection  
3. Run `mvn clean test` again
4. Allure report will show exactly where it failed

## **GitHub Repository**
**Project URL:** https://github.com/hossamsabry15/TestAutomationTask-Luftborn

---

**That's it! Simple eBay automation test with beautiful reports.**
