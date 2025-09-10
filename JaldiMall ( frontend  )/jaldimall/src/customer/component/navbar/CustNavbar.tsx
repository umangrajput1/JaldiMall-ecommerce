import React from "react";
import {
  Avatar,
  Box,
  Button,
  IconButton,
  useMediaQuery,
  useTheme,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import SearchIcon from "@mui/icons-material/Search";
import { AddShoppingCart, FavoriteBorder, Storefront } from "@mui/icons-material";

function CustNavbar() {
  const theme = useTheme();
  const isLarge = useMediaQuery(theme.breakpoints.up("lg"));

  return (
    <Box>
    
      <div className="flex items-center justify-between px-5 lg:px-16 h-[70px] border-b bg-white shadow-sm">
        
        <div className="flex items-center gap-3">
          { !isLarge && <IconButton>
            <MenuIcon sx={{ fontSize: 28, color: "#333" }} />
          </IconButton>}

          <h1 className="cursor-pointer text-xl md:text-2xl font-extrabold text-primary-color tracking-wide">
            JaldiMall
          </h1>
        </div>

        {/* Middle: Categories (visible on large screens) */}
        {isLarge && (
          <ul className="flex items-center font-medium text-gray-700">
            {["Men", "Women", "Home & Furniture", "Electronics"].map((item) => (
              <li
                key={item}
                className="mainCategory px-5 h-[70px] flex items-center cursor-pointer hover:text-primary-color hover:border-b-2 border-primary-color transition-all duration-200"
              >
                {item}
              </li>
            ))}
          </ul>
        )}

        {/* Right Side: Search + User + Icons */}
        <div className="flex items-center gap-2 lg:gap-5">
          {/* Search */}
          <IconButton>
            <SearchIcon sx={{ fontSize: 26, color: "#555" }} />
          </IconButton>

          {/* User / Login */}
          {true ? (
            <Button className="flex items-center gap-2 hover:bg-gray-100 px-2 py-1 rounded-lg">
              <Avatar
                src="https://i.pravatar.cc/150?img=3"
                alt="User Avatar"
                sx={{ width: 30, height: 30 }}
              />
              <h1 className="font-medium hidden lg:block text-gray-700">
                User
              </h1>
            </Button>
          ) : (
            <Button
              variant="contained"
              sx={{
                textTransform: "none",
                borderRadius: "20px",
                backgroundColor: "#15eefa",
                ":hover": { backgroundColor: "#0ec4d4" },
              }}
            >
              Login
            </Button>
          )}

          {/* Wishlist */}
          <IconButton>
            <FavoriteBorder sx={{ fontSize: 26, color: "#444" }} />
          </IconButton>

          {/* Cart */}
          <IconButton>
            <AddShoppingCart sx={{ fontSize: 26, color: "#444" }} />
          </IconButton>

          {/* Become Seller (only large screens) */}
          {isLarge && (
            <Button
              variant="outlined"
              startIcon={<Storefront />}
              sx={{
                textTransform: "none",
                borderRadius: "20px",
                px: 2,
                borderColor: "#15eefa",
                color: "#15eefa",
                ":hover": { borderColor: "#0ec4d4", color: "#0ec4d4" },
              }}
            >
              Become a Seller
            </Button>
          )}
        </div>
      </div>
    </Box>
  );
}

export default CustNavbar;
