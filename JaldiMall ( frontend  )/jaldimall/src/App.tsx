import React from 'react';
import CustNavbar from './customer/component/navbar/CustNavbar';
import { ThemeProvider } from '@mui/material';
import CustomeTheme from './theme/CustomeTheme';
import Home from './customer/pages/home/Home';

function App() {
  return (
    <>

      <ThemeProvider theme={CustomeTheme}>
        
        <div>
          <CustNavbar />
          <Home />
        </div>
      
      </ThemeProvider>  
      
    </>
  );
}

export default App;
