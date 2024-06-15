# Stage 1: Build the application
FROM node:lts AS development
# Set working directory
WORKDIR /app
COPY package.json /app/package.json
COPY package-lock.json /app/package-lock.json
# Same as npm install
RUN npm i --force
COPY . /app

# Build the application
RUN npm run build

# Stage 2: Serve the application with nginx
FROM nginx:alpine
# Copy nginx configuration
COPY --from=development /app/nginx.conf /etc/nginx/nginx.conf
WORKDIR /usr/share/nginx/html
# Remove default nginx static assets
RUN rm -rf ./*
# Copy static assets from builder stage
COPY --from=development /app/build .
# Containers run nginx with global directives and daemon off
ENTRYPOINT ["nginx", "-g", "daemon off;"]
