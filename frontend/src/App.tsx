import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "./routes/MainPage";
import EditPage from "./routes/EditPage";
import LoginPage from "./public/LoginPage";
import RegisterPage from "./public/RegisterPage";

function App() {
  return (
      <div>
          <BrowserRouter>
              <Routes>
                  <Route path="/" element={<LoginPage />} />
                  <Route path="/register" element={<RegisterPage />} />
                  <Route path="/kanban/" element={<MainPage />} />
                  <Route path="/kanban/:id" element={<EditPage />} />
              </Routes>
          </BrowserRouter>
      </div>
  );
}

export default App;
