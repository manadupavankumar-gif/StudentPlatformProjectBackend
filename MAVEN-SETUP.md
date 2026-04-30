# Quick Maven Setup for STS

## Step 1: Import in STS
1. Open STS
2. **File** > **Import** > **Maven** > **Existing Maven Projects**
3. Browse to: `StudentPlatform-Backend-Maven` folder
4. Select folder > **Finish**

## Step 2: Wait for Dependencies
- Look at bottom-right corner for Maven progress
- Wait for "Maven Dependencies" to appear in project

## Step 3: Run Application
1. Right-click project > **Run As** > **Spring Boot App**
2. OR right-click `StudentPlatformApplication.java` > **Run As** > **Java Application**

## Step 4: Verify Running
- Console: `Started StudentPlatformApplication`
- Access: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console

## Step 5: Run Frontend (VS Code)
```bash
cd ../studentplatform
npm run dev
```

## Test Registration
- Frontend: http://localhost:5173-5176
- Should work without "Failed to fetch" error

## If Issues Occur:
- **Maven errors**: Right-click project > Maven > Update Project
- **Port conflict**: Change `server.port=8081` in application.properties
- **Java version**: Update `<java.version>` in pom.xml

## Success Indicators:
- Project shows "Maven" nature in STS
- Maven Dependencies folder appears
- Application starts without errors
- Frontend can register users successfully
