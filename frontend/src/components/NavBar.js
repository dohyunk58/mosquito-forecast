import { Box, Flex, Text, Spacer } from '@chakra-ui/react'
import { Link } from 'react-router-dom'

export default function NavBar() {
    return (
        <Box bg="blue.500" color="white" px={4} py={2}>
            <Flex align="center">
                <Text fontSize="xl" fontWeight="bold">
                    <Link to="/home">
                        I Hate Mosquitos.
                    </Link>
                </Text>
                <Spacer />
                <Link to="/home" style={{ padding: '0 8px', textDecoration: 'none', color: 'white' }} _hover={{ textDecoration: 'none', color: 'blue.300' }}>
                    Home
                </Link>
                <Link to="/about" style={{ padding: '0 8px', textDecoration: 'none', color: 'white' }} _hover={{ textDecoration: 'none', color: 'blue.300' }}>
                    About
                </Link>
                <Link to="/services" style={{ padding: '0 8px', textDecoration: 'none', color: 'white' }} _hover={{ textDecoration: 'none', color: 'blue.300' }}>
                    Services
                </Link>
                <Link to="/github" style={{ padding: '0 8px', textDecoration: 'none', color: 'white' }} _hover={{ textDecoration: 'none', color: 'blue.300' }}>
                    Github
                </Link>
                <Link to="/contact" style={{ padding: '0 8px', textDecoration: 'none', color: 'white' }} _hover={{ textDecoration: 'none', color: 'blue.300' }}>
                    Contact
                </Link>
            </Flex>
        </Box>
    );
}
