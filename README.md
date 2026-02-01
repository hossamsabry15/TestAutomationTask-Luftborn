# **eBay Search Automation Test**

## **Quick Start**
```bash
# 1. Download project
git clone [your-repo-url]

# 2. Go to folder
cd TestAutomationTask

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

# Open the report
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
TestAutomationTask/
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
To see the fancy reports:
```bash
# Windows (using scoop)
scoop install allure

# Mac
brew install allure

# Linux
sudo apt-add-repository ppa:qameta/allure
sudo apt-get update
sudo apt-get install allure
```

## **Need Help?**
If test fails:
1. Update Chrome browser
2. Check internet connection
3. Run `mvn clean test` again
4. Allure report will show exactly where it failed

## **Upload to GitHub**
```bash
git init
git add .
git commit -m "First commit"
git remote add origin [your-github-url]
git push -u origin main
```

---

**That's it! Simple eBay automation test with beautiful reports.**