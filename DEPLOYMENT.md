# Deploy Student Platform Backend to Render

## Prerequisites
- Git repository with your Spring Boot application
- Render account (free tier available)
- Your code pushed to GitHub/GitLab/Bitbucket

## Deployment Steps

### 1. Push Code to Repository
```bash
git add .
git commit -m "Configure for Render deployment"
git push origin main
```

### 2. Deploy on Render
1. Go to [render.com](https://render.com)
2. Sign up/login and connect your Git repository
3. Click **New +** → **Web Service**
4. Connect your repository
5. Render will detect the `render.yaml` file automatically
6. Configure environment variables:
   - `FRONTEND_URL`: Your frontend application URL (e.g., `https://your-app.onrender.com`)
7. Click **Create Web Service**

### 3. Database Setup
The `render.yaml` file automatically creates a PostgreSQL database. Render will:
- Create the database instance
- Set the `DATABASE_URL` environment variable
- Connect it to your application

### 4. Environment Variables
Render automatically sets:
- `DATABASE_URL`: PostgreSQL connection string
- `DATABASE_USER`: Database username  
- `DATABASE_PASSWORD`: Database password
- `JWT_SECRET`: Auto-generated JWT secret
- `JWT_EXPIRATION`: Token expiration time
- `SPRING_PROFILES_ACTIVE`: Set to `production`

### 5. Health Check
The application includes a health endpoint at `/api/health` that Render uses to verify your app is running.

### 6. CORS Configuration
Update the `FRONTEND_URL` environment variable in Render to match your frontend domain:
- Example: `https://your-frontend-app.onrender.com`

## Files Created for Deployment

### `render.yaml`
- Configures web service and PostgreSQL database
- Sets build and start commands
- Defines environment variables

### `application-production.properties`
- Production configuration using PostgreSQL
- Environment variable placeholders
- Production-optimized settings

### `Procfile`
- Tells Render how to start your application

### `HealthController.java`
- Provides `/api/health` endpoint for health checks

## Troubleshooting

### Build Fails
- Check that all files are committed to Git
- Verify Maven dependencies in `pom.xml`
- Check build logs in Render dashboard

### Database Connection Issues
- Verify PostgreSQL dependency is in `pom.xml`
- Check `DATABASE_URL` environment variable
- Ensure database is created in Render dashboard

### CORS Issues
- Set `FRONTEND_URL` environment variable correctly
- Check that your frontend URL is accessible

### Application Not Starting
- Check logs in Render dashboard
- Verify health endpoint is accessible
- Ensure port configuration is correct

## Local Development
To run locally with production settings:
```bash
export SPRING_PROFILES_ACTIVE=production
mvn spring-boot:run
```

## Cost
- Free tier: 750 hours/month (sufficient for always-on deployment)
- PostgreSQL: Free tier available
- No credit card required for free tier
