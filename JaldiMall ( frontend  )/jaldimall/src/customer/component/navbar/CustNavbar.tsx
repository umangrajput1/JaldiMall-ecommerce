import React from "react";
import { Avatar, Box, Button, IconButton } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import SearchIcon from "@mui/icons-material/Search";

function CustNavbar() {
  return (
    <Box>
      {/* Navbar Container */}
      <div className="flex items-center justify-between px-5 lg:px-20 h-[70px] border-b-2 bg-white">
        
        {/* Left Side: Logo + Menu */}
        <div className="flex items-center gap-3">
          <IconButton>
            <MenuIcon />
          </IconButton>

          <h1 className="logo cursor-pointer text-lg md:text-2xl font-bold text-[#15eefa]">
            JaldiMall
          </h1>
        </div>

        {/* Right Side: Search + User */}
        <div className="flex items-center gap-4">
          <IconButton>
            <SearchIcon />
          </IconButton>

          {true ? (
            <Button className="flex items-center gap-2">
              <Avatar
              src="https://i.pravatar.cc/150?img=3"
              alt="User Avatar"
              />
              <h1 className="font-semibold hidden lg:block">User</h1>
            </Button>
          ) : (
            <Button variant="contained">Login</Button>
          )}
        </div>
      </div>
    </Box>
  );
}

export default CustNavbar;
