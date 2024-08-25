/* eslint-disable */
import { Routes, Route } from 'react-router-dom'
import { ChakraProvider, Box, Button } from '@chakra-ui/react'
import './App.css';
import NavBar from './components/NavBar'
import Home from './components/Home'
import Graph from './components/Graph'

function App() {
  return (
    <ChakraProvider>
      <NavBar />
      <Box bg="gray.100" h="100vh">
        <Routes>
            <Route path="/" element={ <Graph /> }/>
            <Route path="/home" element={ <Home /> }/>
            <Route path="/about" element={<Graph />}/>
            <Route path="/services" element={<Graph />}/>
            <Route path="/github" element={<Graph />}/>
            <Route path="/contact" element={<Graph />}/>
        </Routes>
      </Box>
    </ChakraProvider>
  );
}

export default App;
