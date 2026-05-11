# AI Multimedia Assistant

An AI-powered multimedia assistant that allows users to upload PDFs, audio, and video files, perform semantic search, ask contextual questions using Retrieval-Augmented Generation (RAG), and navigate multimedia content using AI-generated timestamps.

## Features

- Upload PDF, audio, and video files
- AI-powered document question answering
- Retrieval-Augmented Generation (RAG)
- Semantic chunk search
- AI-generated summaries
- Whisper-based transcription
- Timestamp-based multimedia navigation
- Video jump-to-timestamp feature
- React chat interface
- Spring Boot REST APIs
- MySQL database integration
- Groq LLM integration

## Architecture

````text
Frontend (React + TypeScript)
        ↓
Spring Boot Backend APIs
        ↓
Document Processing Layer
        ↓
RAG Retrieval Engine
        ↓
Groq LLM / Whisper APIs
        ↓
MySQL Database



---

```markdown id="r2q7xn"
## Tech Stack

### Frontend
- React
- TypeScript
- Tailwind CSS
- Axios
- Vite

### Backend
- Java 21
- Spring Boot
- Spring Security
- JPA / Hibernate
- Maven

### AI / ML
- Groq LLM API
- Llama 3
- Whisper Transcription
- Sentence Embeddings
- RAG Pipeline

### Database
- MySQL

### Deployment
- Docker
- Render

## Project Structure

```text
ai-document-qa-app/
│
├── backend/
│   ├── controller/
│   ├── service/
│   ├── entity/
│   ├── repository/
│   └── config/
│
├── frontend/
│   ├── src/
│   ├── components/
│   ├── pages/
│   └── api/

## Setup Instructions

```markdown id="x9q4vn"
## Local Setup

### Backend Setup

```bash
cd backend

./mvnw clean install

./mvnw spring-boot:run

Backend runs on: http://localhost:8080

### Frontend Setup

cd frontend

npm install

npm run dev

Frontend runs on: http://localhost:5173

```markdown id="p7q1xn"
## API Endpoints

### Upload File
```http
POST /api/files/upload

### Ask AI Question
```http
POST /api/chat

### Generate Summary
```http
POST /api/summary/{fileId}

### Timestamp Search
```http
GET /api/timestamps/{fileId}?query=AWS

## Screenshots

### Dashboard

![Dashboard](screenshots/dashboard.png)

### AI Chat

![Chat](screenshots/chat.png)

### Video Timestamp Navigation

![Video](screenshots/video-player.png)

## Future Improvements

- Multi-document RAG retrieval
- Vector database integration
- Streaming AI responses
- Authentication and authorization
- Cloud storage integration
- Real-time collaboration
- Advanced timestamp alignment
- Open-source embedding models

## Author

Abhinav

AI Multimedia Assistant Project

## License

This project is developed for educational and assignment purposes.
````
