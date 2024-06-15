# Stage 1: Build the application
FROM node:20 AS build-stage

WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package.json package-lock.json ./

# Install dependencies using npm
RUN npm install --force

# Copy the entire source code into the container
COPY . .

# Build the application
RUN npm run build --force

# Stage 2: Serve the application with nginx
FROM nginx:alpine

# Copy build files from the build stage
COPY --from=build-stage /app/build /usr/share/nginx/html

# Expose port 80 for access
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
